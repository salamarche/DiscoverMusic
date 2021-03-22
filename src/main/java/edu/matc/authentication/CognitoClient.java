package edu.matc.authentication;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import edu.matc.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/*@author: DEVPROBLEMS(A SARANG KUMAR TAK)*/
public class CognitoClient implements PropertiesLoader {
    private AWSCognitoIdentityProvider client ;
    private String clientId = "CLIENT_ID";
    private String userPool = "USER_POOL_ID";
    private final Logger logger = LogManager.getLogger(this.getClass());

    public CognitoClient() {
        client = createCognitoClient();
    }

    private AWSCognitoIdentityProvider createCognitoClient() {
        //String clientId = null;
        String clientSecret = null;

        Properties awsCredentials = new Properties();
        try {
            awsCredentials = loadProperties("/awsCredentials.properties");
            clientId = awsCredentials.getProperty("clientId");
            clientSecret = awsCredentials.getProperty("clientSecret");

        } catch (IOException iOException) {
            logger.error("CognitoClient/AWSCognitoIdentityProvider failed to load properties..." + iOException);

        } catch (Exception exception) {
            logger.error("CognitoClient/AWSCognitoIdentityProvider failed to load properties..." + exception);
        }


        AWSCredentials cred = new BasicAWSCredentials(clientId, clientSecret);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    public SignUpResult signUp(String name, String email, String password) {

        SignUpRequest request = new SignUpRequest().withClientId(clientId ).withUsername(email).withPassword(password);
        SignUpResult result = client.signUp(request);
        return result;
    }

    public ConfirmSignUpResult confirmSignUp(String email, String confirmationCode) {
        ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(clientId).withUsername(email).withConfirmationCode(confirmationCode);
        return client.confirmSignUp(confirmSignUpRequest);
    }

    public Map<String, String> login(String email, String password) {
        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
        }};

        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withUserPoolId(userPool)
                .withClientId(clientId)
                .withAuthParameters(authParams);
        AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
        AuthenticationResultType resultType = authResult.getAuthenticationResult();
        return new LinkedHashMap<String, String>() {{
            put("idToken", resultType.getIdToken());
            put("accessToken", resultType.getAccessToken());
            put("refreshToken", resultType.getRefreshToken());
            put("message", "Successfully login");
        }};

    }
}
