package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DiscoverFavorite controls logic from discover.jsp for adding an artist to user collection
 */
@WebServlet(
        urlPatterns = {"/favorite"}
)
public class DiscoverFavorite extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Adds artist to user collection.
     * Checks to see if user is logged in, if so checks for existing artist. If artist does not exist adds artist.
     * Returns user to previous page.
     *
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //check to see that user is logged in
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        String url = "";

        if (userId != null) {
            //add user engagement

            GenericDao userDao = new GenericDao(User.class);
            GenericDao artistDao = new GenericDao(Artist.class);
            GenericDao artistEngagementDao = new GenericDao(ArtistEngagement.class);

            User user = (User) userDao.getById(userId);
            int artistId = Integer.parseInt(req.getParameter("artistId"));
            Artist artist = (Artist) artistDao.getById(artistId);

            //check for existing artist engagement before adding it.
            Set<ArtistEngagement> engagements = user.getArtistUserEngagement();
            Boolean artistFound = false;

            for (ArtistEngagement engagement : engagements) {
                Artist thisArtist = engagement.getArtist();
                if (thisArtist.getId() == artistId ) {
                    artistFound = true;
                    break;
                }
            }

            if (artistFound == false) {
                LocalDateTime dateTime = LocalDateTime.now();
                ArtistEngagement engagement = new ArtistEngagement(artist, user, dateTime);
                artistEngagementDao.insert(engagement);
            }



            //return to previous page
            String cityId = req.getParameter("cityId");
            url = "./discoverAction?selectedCityId=" + cityId;

        } else {

            url = "login";
        }
            resp.sendRedirect(url);


    }
}
