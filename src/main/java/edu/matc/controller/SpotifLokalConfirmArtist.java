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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/confirm-artist"}
)
public class SpotifLokalConfirmArtist extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idEntered = req.getParameter("spotifyId");
        Artist artist;
        boolean isFound = false;


        if (idEntered != null) {
            artist = retrieveArtist(idEntered);

            if (artist.getSpotifyId() != null ) {
                isFound = true;

                Map<Integer, String> artistLocations = new HashMap<>();
                artistLocations = artist.readableLocations();
                req.setAttribute("artistLocations", artistLocations);

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

                artist.setArtistName(name);
                artist.setSpotifyId(spotifyId);
                artist.setAvatarUrl(imageUrl);
                artist.setDescription(description);

                artistDao.insert(artist);
            }
        }
            return artist;
    }




}
