package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@WebServlet(
        urlPatterns = {"/user"}
)
public class UserPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");


        if (userId != null) {

            GenericDao userDao = new GenericDao(User.class);
            User user = (User) userDao.getById(userId);
            Set<ArtistEngagement> engagements = user.getArtistUserEngagement();


            Map<LocalDateTime, Artist> artists = new TreeMap<>(Collections.reverseOrder());

            for (ArtistEngagement e : engagements) {

                Artist artist = e.getArtist();
                LocalDateTime dateTime = e.getEngagementDate();
                artists.put(dateTime, artist);

            }

            req.setAttribute("artists", artists);

            RequestDispatcher dispatcher = req.getRequestDispatcher("user.jsp");
            dispatcher.forward(req, resp);

        } else {
            resp.sendRedirect("login");
        }


    }

}
