package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@WebServlet(
        urlPatterns = {"/loginAction"}
)


public class LoginAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String uriStr = req.getParameter("uri");

        try {
            URI uri = new URI(uriStr);
            String fragment = uri.getFragment();
            logger.info("fragment: " + fragment);
        } catch (URISyntaxException e) {
            logger.error("LoginAction/doPost: Could not establish uri ... " + e);
        }

        String hash = req.getParameter("hash");
        logger.info("hash: " + hash);

        String idToken = req.getParameter("idToken");
        logger.info ("idToken: " + idToken);


        String accessToken = req.getParameter("accessToken");
        logger.info ("accessToken: " + accessToken);


        //forward to index
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);


    }
}
