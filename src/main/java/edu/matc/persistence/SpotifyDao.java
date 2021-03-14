package edu.matc.persistence;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpotifyDao {
    private final Logger logger = LogManager.getLogger(this.getClass());

    String getAccessToken() {
        String accessToken = null;
        HttpRequest request = null;
        UrlEncodedFormEntity formEntity = null;
        //Header
        //Authorization: Basic Mzg0NWYxYmZlODY2NDRmMDkyMWY2ZTkyNWU3OTFjNWU6NTIyYjc1YzM5YWI2NGIxMGI1MWZjNWZlZWJhZDhlYjg=

        //Body x-www-form-urlencoded
        //grant_type: client_credentials

        // Trying to encode the body
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "client_credentials");

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

        for (String key : body.keySet()) {
            nameValuePairList.add(new BasicNameValuePair(key, body.get(key)));
        }

        try {
            formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("SpotifyDao/getAccessToken encoding form entity..." + e);
        }

        //Creating the POST request
        /*
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI("https://accounts.spotify.com/api/token"))
                    .headers("Authorization", "Basic Mzg0NWYxYmZlODY2NDRmMDkyMWY2ZTkyNWU3OTFjNWU6NTIyYjc1YzM5YWI2NGIxMGI1MWZjNWZlZWJhZDhlYjg=")
                    .POST(HttpRequest.BodyProcessor.fromString(formEntity))
                    .build();
        } catch (URISyntaxException e) {
            logger.error("SpotifyDao/getAccessToken: URISyntaxException ..." + e);
        }
        */
        return accessToken;
    }
}
