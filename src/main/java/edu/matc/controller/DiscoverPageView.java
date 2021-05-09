package edu.matc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DiscoverPageView routes to discover.jsp
 */
@WebServlet(
        urlPatterns = {"/discover"}
)
public class DiscoverPageView extends HttpServlet {
    /**
     * Routes user to discover.jsp
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/discover.jsp");
        dispatcher.forward(req, resp);

    }
}
