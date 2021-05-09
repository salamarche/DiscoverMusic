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
        urlPatterns = {"/add-location"}
)
public class SpotifLokalAddLocation extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cityId = Integer.parseInt(req.getParameter("selectedCityId"));
        GenericDao cityDao = new GenericDao(City.class);
        City city = (City) cityDao.getById(cityId);

        int artistId = Integer.parseInt(req.getParameter("artistId"));
        GenericDao artistDao = new GenericDao(Artist.class);
        Artist artist = (Artist) artistDao.getById(artistId);

        artist.addCity(city);
        artistDao.saveOrUpdate(artist);

        req.setAttribute("artist", artist);
        req.setAttribute("isFound", true);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/spotifLokal.jsp");
        dispatcher.forward(req, resp);


    }


}
