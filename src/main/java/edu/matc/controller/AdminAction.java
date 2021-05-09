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
import java.util.Set;

/**
 * AdminAction handles the logic from admin.jsp
 * Contains a doPost for the select-artist form
 */
@WebServlet(
        urlPatterns = {"/admin-action"}
)
public class AdminAction extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * doPost handles the form select-artist, and forwards to the correct page depending on the submit value:
     *  - deletes an artist and returns the admin page.
     *  - forwards to adminEditArtist to edit artist description.
     *  - forwards to spotifLokal with the artist selected for location editing.
     *
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao artistDao = new GenericDao(Artist.class);
        int artistId = Integer.parseInt(req.getParameter("selectedArtist"));
        Artist artist = (Artist)artistDao.getById(artistId);


        if (req.getParameter("submit").equals("delete")) {

            GenericDao artistEngagementDao = new GenericDao(ArtistEngagement.class);
            Set<ArtistEngagement> artistEngagementSet = artist.getArtistUserEngagement();
            for (ArtistEngagement ae : artistEngagementSet) {
                artistEngagementDao.delete(ae);
            }

            artistDao.delete(artist);
            resp.sendRedirect("admin");
        }

        if (req.getParameter("submit").equals("edit")) {
            String url = "adminEditArtist.jsp";

            req.setAttribute("artist", artist);

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }

        if (req.getParameter("submit").equals("location")) {
            String url = "/spotifLokal.jsp";

            req.setAttribute("artist", artist);
            req.setAttribute("isFound", true);

            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }



    }
}
