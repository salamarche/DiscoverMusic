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

@WebServlet(
        urlPatterns = {"/remove-location"}
)
public class SpotifLokalRemoveLocation extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int artistId = Integer.parseInt(req.getParameter("artistId"));
        int cityId = Integer.parseInt(req.getParameter("location"));

        logger.info("artist id: " + artistId + "\ncity id: " + cityId);

        GenericDao<City> cityDao = new GenericDao(City.class);
        GenericDao<Artist> artistDao = new GenericDao(Artist.class);
        City city = cityDao.getById(cityId);
        Artist artist = artistDao.getById(artistId);
        artist.removeCity(city);
        artistDao.saveOrUpdate(artist);

        req.setAttribute("artist", artist);
        req.setAttribute("isFound", true);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/spotifLokal.jsp");
        dispatcher.forward(req, resp);


    }
}
