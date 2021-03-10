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

@WebServlet (
        urlPatterns = {"/discoverAction"}
)

public class DiscoverAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityEntered = req.getParameter("city");
        String countryEntered = req.getParameter("country");

        logger.info(countryEntered);
        logger.info(cityEntered);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/discover.jsp");
        dispatcher.forward(req, resp);
    }
}
