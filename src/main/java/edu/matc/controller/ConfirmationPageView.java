package edu.matc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Enumeration;

@WebServlet (
        urlPatterns = {"/confirmation"}
)

public class ConfirmationPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Getting this from the launch hosted UI
        //
        //https://discover-music.auth.us-east-2.amazoncognito.com/login?client_id=<id>&response_type=code&scope=aws.cognito.signin.user.admin+email+openid+phone+profile&redirect_uri=http://localhost:8080/DiscoverMusic_war/confirmatio
        logger.info(req.getParameter("code"));

        //Getting these from custom URI
        //https://discover-music.auth.us-east-2.amazoncognito.com/login?response_type=token&client_id=<id>&redirect_uri=http://localhost:8080/DiscoverMusic_war/confirmation
        //http://localhost:8080/DiscoverMusic_war/confirmation.jsp#id_token=<token>&access_token=<token>&expires_in=3600&token_type=Bearer

        logger.info(req.getParameter("id_token")); //returning null
        logger.info(req.getParameter("access_token")); //returning null

        //What are the parameters?????
        logger.info(req.getParameterNames().hasMoreElements()); //returning false.
        Enumeration<String> names = req.getParameterNames();

        while (names.hasMoreElements()) {
            Object obj = names.nextElement();
            String param = (String)obj;
            String value = req.getParameter(param);
            logger.info("param: " + param);
            logger.info("value: " + value);

        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/confirmation.jsp");
        dispatcher.forward(req, resp);

    }
}
