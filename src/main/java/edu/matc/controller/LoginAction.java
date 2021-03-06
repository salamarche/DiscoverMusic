package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.utilities.PropertiesLoader;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * LoginAction controls login action submitted from confirm.jsp
 */
@WebServlet(
        urlPatterns = {"/loginAction"}
)
public class LoginAction extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * receives idToken from confirm.jsp and adds user to session.
     * Calls decodeToken to decode the idToken.
     * Calls lookUpUser to look up user in existing db.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hash = req.getParameter("hash");
        String idToken = req.getParameter("idToken");
        String accessToken = req.getParameter("accessToken");


        //decode token, retrieve user
        Map<String, Object> claims = decodeToken(idToken);
        String email = (String) claims.get("email");
        String username = (String) claims.get("cognito:username");
        User user = lookUpUser(email, username);

        //Add user to session
        HttpSession session = req.getSession();
        session.setAttribute("username", user.getUserName());
        session.setAttribute("userRole", user.getUserRole());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("userId", user.getId());

        //forward to index
        resp.sendRedirect("./");

    }

    /**
     * Decodes the token received after login to verify the user
     * @param jwt Json Web Token aka id_token
     */
    @SneakyThrows
    public Map<String, Object> decodeToken(String jwt) {
        String clientId = null;
        String httpsJwks = null;
        String expectedIssuer = null;
        String email = null;
        String username = null;
        Map<String, Object> claims = null;
        Properties awsCredentails = new Properties();

        try {
            awsCredentails = loadProperties("/awsCredentials.properties");
            clientId = awsCredentails.getProperty("clientId");
            httpsJwks = awsCredentails.getProperty("httpsJwks");
            expectedIssuer = awsCredentails.getProperty("expectedIssuer");

        } catch (IOException iOException) {
            logger.error("LoginAction/authorizeUser failed to load properties..." + iOException);

        } catch (Exception exception) {
            logger.error("LoginAction/authorizeUser failed to load properties..." + exception);
        }

        // The HttpsJwks retrieves and caches keys from a the given HTTPS JWKS endpoint.
        // Because it retains the JWKs after fetching them, it can and should be reused
        // to improve efficiency by reducing the number of outbound calls the the endpoint.
        HttpsJwks httpsJkws = new HttpsJwks(httpsJwks);

        // The HttpsJwksVerificationKeyResolver uses JWKs obtained from the HttpsJwks and will select the
        // most appropriate one to use for verification based on the Key ID and other factors provided
        // in the header of the JWS/JWT.
        HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);

        // Use JwtConsumerBuilder to construct an appropriate JwtConsumer, which will
        // be used to validate and process the JWT.
        // The specific validation requirements for a JWT are context dependent, however,
        // it typically advisable to require a (reasonable) expiration time, a trusted issuer, and
        // and audience that identifies your system as the intended recipient.
        // If the JWT is encrypted too, you need only provide a decryption key or
        // decryption key resolver to the builder.
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer(expectedIssuer) // whom the JWT needs to have been issued by
                .setExpectedAudience(clientId) // to whom the JWT is intended for
                .setVerificationKeyResolver(httpsJwksKeyResolver)
                .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
                        AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256) // which is only RS256 here
                .build(); // create the JwtConsumer instance


        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            logger.info("JWT validation succeeded! " + jwtClaims);
            claims = jwtClaims.getClaimsMap();

            //logger.info(claims.get("email"));
            //logger.info(claims.get("cognito:username"));
        } catch (InvalidJwtException e) {
            // InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
            // Hopefully with meaningful explanations(s) about what went wrong.
            logger.error("LoginAction/ doPost: Could not validate the jwt..." + e);

            // Programmatic access to (some) specific reasons for JWT invalidity is also possible
            // should you want different error handling behavior for certain conditions.
            // Whether or not the JWT has expired being one common reason for invalidity
            if (e.hasExpired()) {
                logger.error("JWT expired at " + e.getJwtContext().getJwtClaims().getExpirationTime());
            }

            // Or maybe the audience was invalid
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                logger.error("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
            }
        }

        return claims;
    }

    /**
     * lookUpUser looks for a user in the database.
     * If found just returns user; if not found it creates a new user with email and username
     *
     * @param email user email
     * @param username user username
     * @return User object
     */
    public User lookUpUser(String email, String username) {
        GenericDao dao = new GenericDao(User.class);

        List<User> users =  dao.getByPropertyEqual("email", email);
        User user = null;

        if (users.size() == 1) {
            logger.info("user found");
            user = users.get(0);

        } else if (users.size() == 0) {
            logger.info("new user!");
            user = new User();
            user.setEmail(email);
            user.setUserName(username);
            user.setUserRole("general");

            dao.saveOrUpdate(user);

        }

        return user;

    }


}
