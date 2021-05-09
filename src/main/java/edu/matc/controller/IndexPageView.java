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

/**
 * IndexPageView controls route to index
 */
@WebServlet(
            name = "IndexPageView",
            urlPatterns = {""}
    )
public class IndexPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Routes user to index page. Pulls and updates up to 6 recently discovered artists
     * @param req requests
     * @param resp reponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        GenericDao engagementDao = new GenericDao(ArtistEngagement.class);
        List<ArtistEngagement> engagements = engagementDao.getAll();
        Collections.sort(engagements);

        Set<Artist> discoveredArtists = new HashSet<>();

        if (engagements.size() < 6 && engagements.size() > 0 ) {
            for (int i = 0; i < engagements.size(); i++) {
                discoveredArtists.add(engagements.get(i).getArtist());
            }
        } else if (engagements.size() > 6 || engagements.size() == 6) {
            for (int i = 0; i < 6; i++) {
                discoveredArtists.add(engagements.get(i).getArtist());
            }
        } else if (engagements.size() == 0) {

        }

        req.setAttribute("discoveredArtists", discoveredArtists);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
