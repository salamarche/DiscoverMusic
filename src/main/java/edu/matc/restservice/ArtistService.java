package edu.matc.restservice;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/artists")
public class ArtistService {
    @GET
    @Produces("text/plain")
    public Response getArtists() {
        String output = null;
        return Response.status(200).entity(output).build();
    }

}



