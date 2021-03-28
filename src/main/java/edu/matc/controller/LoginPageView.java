package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import edu.matc.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/login"}
)

public class LoginPageView extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO I wanted to protect my client id but idk if I can...this isn't working
        Properties awsProperties = new Properties();
        String loginLink = null;
        try {
            awsProperties = loadProperties("/awsCredentials.properties");
            loginLink = awsProperties.getProperty("loginLink");

        } catch (IOException iOException) {
            logger.error("LoginAction/authorizeUser failed to load properties..." + iOException);

        } catch (Exception exception) {
            logger.error("LoginAction/authorizeUser failed to load properties..." + exception);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(loginLink);
        dispatcher.forward(req, resp);
    }

}
