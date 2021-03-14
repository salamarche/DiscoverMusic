package edu.matc.persistence;

import com.wrapper.spotify.SpotifyApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class SpotifyAuthorizationTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void testSpotify() {

        SpotifyAuthorization auth = new SpotifyAuthorization();
        auth.clientCredentials_Sync();


    }
}
