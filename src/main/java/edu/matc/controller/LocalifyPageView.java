package edu.matc.controller;

import edu.matc.entity.Country;
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
        urlPatterns = {"/location"}
)

public class GetLocationPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao countryDao = new GenericDao(Country.class);
        req.setAttribute("countries", countryDao.getAll());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/locationFormElements.jsp");
        dispatcher.forward(req, resp);
    }

}
