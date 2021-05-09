package edu.matc.controller;

import com.wrapper.spotify.model_objects.specification.Image;
import edu.matc.entity.Artist;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.SpotifyAPIDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * SpotifLokalConfirmArtist controls action from SptoifLokal.jsp confirm artist form.
 */
@WebServlet(
        urlPatterns = {"/confirm-artist"}
)
public class SpotifLokalConfirmArtist extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Validates the confirm artist form.
     * Calls retrieve artist to obtain artist (null if not found) and forwards to spotifLokal with artist attribute set.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idEntered = req.getParameter("spotifyId");
        Artist artist;
        boolean isFound = false;


        if (idEntered != null) {
            artist = retrieveArtist(idEntered);

            if (artist.getSpotifyId() != null ) {
                isFound = true;


            } else {
                logger.info("SpotifLokalConfirmArtist: artist not found");
                isFound = false;
            }

            req.setAttribute("artist", artist);
            req.setAttribute("isFound", isFound);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/spotifLokal.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Retrieves an artist either from the database or from Spotify API
     * Using spotifyId, checks for existing Artist in db. If not found attempts to retrieve from Spotify, and adds that
     * artist to the database. Returns user (may be null)
     *
     * @param spotifyId String of spotify Artist Id
     * @return Artist object
     */
    public Artist retrieveArtist(String spotifyId) {
        GenericDao artistDao = new GenericDao(Artist.class);
        Artist artist = null;

        //check for artist in db
        List<Artist> artistReturned = artistDao.getByPropertyEqual("spotifyId", spotifyId);
        if (artistReturned.size() == 1) {
            artist = artistReturned.get(0);
            return artist;
        } else if (artistReturned.size() == 0) {
            artist = new Artist();

            SpotifyAPIDao spotifyAPIDao = new SpotifyAPIDao();
            spotifyAPIDao.clientCredentials_Sync();
            com.wrapper.spotify.model_objects.specification.Artist spotifyArtist = spotifyAPIDao.getSpotifyArtist(spotifyId);

            if (spotifyArtist != null) {
                String name = spotifyArtist.getName();
                Image[] images = spotifyArtist.getImages();
                Image image = images[0];
                String imageUrl = image.getUrl();

                String[] genres = spotifyArtist.getGenres();
                String description = "<ul>";
                for (String genre : genres) {
                    description += "<li>" + genre + "</li>";
                }
                description += "</ul>";

                String href = "https://open.spotify.com/artist/" + spotifyId;


                artist.setArtistName(name);
                artist.setSpotifyId(spotifyId);
                artist.setAvatarUrl(imageUrl);
                artist.setDescription(description);
                artist.setHref((href));

                artistDao.insert(artist);
            }
        }
            return artist;
    }




}
