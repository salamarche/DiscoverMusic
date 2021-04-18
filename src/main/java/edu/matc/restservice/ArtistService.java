package edu.matc.restservice;
import edu.matc.entity.Artist;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/artists")
public class ArtistService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @GET
    @Produces("text/plain")
    public Response getArtists() {
        String output = "Artists: \n";
        GenericDao artistDao = new GenericDao(Artist.class);
        List<Artist> artists = artistDao.getAll();

        for (Artist artist : artists) {
            output += artist.getId() + "\n"
                    + artist.getArtistName() + "\n";
                    //+ artist.getCity() + "\n \n";

        }
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/{param}")
    public Response getArtist(@PathParam("param") int id) {
        String output = "Artist: \n";
        GenericDao artistDao = new GenericDao(Artist.class);
        Artist artist = (Artist) artistDao.getById(id);

        output += artist.getId() + "\n"
                + artist.getArtistName() + "\n";
                //+ artist.getCity() + "\n \n";


        return Response.status(200).entity(output).build();
    }

}



