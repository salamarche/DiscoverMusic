package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/favorite"}
)
public class DiscoverFavorite extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //check to see that user is logged in
        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        String url = "";

        if (userId != null) {
            //add user engagement

            GenericDao userDao = new GenericDao(User.class);
            User user = (User) userDao.getById(userId);
            int artistId = Integer.parseInt(req.getParameter("artistId"));

            //TODO - build out the url like this:
            // http://localhost:8080/DiscoverMusic_war/discoverAction?country=United+States&region=Wisconsin&city=Stevens+Point&selectedCountryId=10&selectedRegionId=255&selectedCityId=1840002320
            // Make a utility class in city for discoverAction parameters
            // Is there a way to NOT submit the text inputs?

            url = "discoverAction";

            logger.info("******************Discover Favorite");
            logger.info(userId);
            logger.info(artistId);
            logger.info(url);
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);


        } else {
            url = "https://discover-music.auth.us-east-2.amazoncognito.com/login?response_type=token&client_id=4lqlga33rukqfjnas0rbi1rnn4&redirect_uri=http://localhost:8080/DiscoverMusic_war/confirmation";
            resp.sendRedirect(url);

        }


    }
}
