package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.entity.City;
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
import java.util.Set;

@WebServlet (
        urlPatterns = {"/discoverAction"}
)

public class DiscoverAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("***********************DiscoverAction");

        String cityName = req.getParameter("city");
        logger.info("City: " + cityName);

        String cityId = req.getParameter("selectedCityId");
        logger.info("City Id: " + cityId);

        GenericDao cityDao = new GenericDao(City.class);
        City city = (City) cityDao.getById(Integer.parseInt(cityId));
        Set<Artist> artists = city.getArtists();
        req.setAttribute("artists", artists);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/discover.jsp");
        dispatcher.forward(req, resp);

    }
}
