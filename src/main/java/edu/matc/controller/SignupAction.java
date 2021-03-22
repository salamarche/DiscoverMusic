package edu.matc.controller;

import com.amazonaws.services.cognitoidp.model.SignUpResult;
import edu.matc.authentication.CognitoClient;
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

@WebServlet(
        urlPatterns = {"/signupAction"}
)


public class SignupAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);


        String emailEntered = req.getParameter("username");
        String password1Entered = req.getParameter("password");
        String password2Entered = req.getParameter("confirmPassword");

        if (password1Entered.equals(password2Entered)) {
            CognitoClient client = new CognitoClient();
            SignUpResult result = client.signUp("NA", emailEntered, password1Entered);
            logger.info(result.toString());
        }




        //forward to index
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);


    }
}
