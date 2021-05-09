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
 * Controls routing to to spotifLokal.jsp
 */
@WebServlet(
        urlPatterns = {"/spotifLokal"}
)
public class SpotifLokalPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Routes user to spotifLokal.jsp
     *
     * @param req request
     * @param resp rewsponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        RequestDispatcher dispatcher = req.getRequestDispatcher("/spotifLokal.jsp");
        dispatcher.forward(req, resp);
    }

}
