package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
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
import java.util.*;

@WebServlet(
            urlPatterns = {""}
    )
public class IndexPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Index Page view working");

        GenericDao engagementDao = new GenericDao(ArtistEngagement.class);
        List<ArtistEngagement> engagements = engagementDao.getAll();
        Collections.sort(engagements);

        Set<Artist> discoveredArtists = new HashSet<>();

        if (engagements.size() < 5 && engagements.size() > 0 ) {
            for (int i = 0; i < engagements.size(); i++) {
                discoveredArtists.add(engagements.get(i).getArtist());
            }
        } else if (engagements.size() > 5) {
            for (int i = 0; i < 5; i++) {
                discoveredArtists.add(engagements.get(i).getArtist());
            }
        } else if (engagements.size() == 0) {
            resp.sendRedirect("index.jsp");
        }

        req.setAttribute("discoveredArtists", discoveredArtists);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
