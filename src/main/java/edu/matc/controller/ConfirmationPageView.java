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

/**
 * Routes user to confirmation.jsp
 */
@WebServlet (
        urlPatterns = {"/confirmation"}
)

public class ConfirmationPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Routes user to confirmation.jsp
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/confirmation.jsp");
        dispatcher.forward(req, resp);

    }
}
