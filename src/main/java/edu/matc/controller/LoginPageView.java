package edu.matc.controller;

import edu.matc.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/login"}
)

public class LoginPageView extends HttpServlet implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO: change for deployment
        //Local
        String url = "https://discover-music.auth.us-east-2.amazoncognito.com/login?response_type=token&client_id=4lqlga33rukqfjnas0rbi1rnn4&redirect_uri=http://localhost:8080/DiscoverMusic_war/confirmation";

        //Aws - This won't work because it won't take http:// protocol, or a straight ip address
        //String url = "https://discover-music.auth.us-east-2.amazoncognito.com/login?response_type=token&client_id=4lqlga33rukqfjnas0rbi1rnn4&redirect_uri=3.140.97.66:8080/DiscoverMusic/confirmation";


        resp.sendRedirect(url);
    }

}
