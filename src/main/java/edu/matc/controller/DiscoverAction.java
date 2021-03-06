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

/**
 * Controls form logic for discover.jsp
 */
@WebServlet (
        urlPatterns = {"/discoverAction"}
)

public class DiscoverAction extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * gets selected city from request and adds request attributes for displaying artists associated with city.
     * Forwards user to discover.jsp
     *
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer cityId = Integer.parseInt(req.getParameter("selectedCityId"));

        GenericDao cityDao = new GenericDao(City.class);
        City city = (City) cityDao.getById(cityId);
        String cityLocation = city.toString();

        Set<Artist> artists = city.getArtists();
        req.setAttribute("artists", artists);
        req.setAttribute("cityLocation", cityLocation);
        req.setAttribute("formSubmitted", true);
        req.setAttribute("cityId", cityId);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/discover.jsp");
        dispatcher.forward(req, resp);

    }
}
