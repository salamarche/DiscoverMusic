package edu.matc.controller;

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
import java.util.*;

/**
 * UserPageView routes user to their individual collection page
 */
@WebServlet(
        urlPatterns = {"/user"}
)
public class UserPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Forwards a user to user.jsp with artist engagement information.
     * If user is not signed in, forwards to login conroller.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Integer userId = (Integer) session.getAttribute("userId");


        if (userId != null) {

            GenericDao userDao = new GenericDao(User.class);
            User user = (User) userDao.getById(userId);
            Set<ArtistEngagement> engagements = user.getArtistUserEngagement();

            //Putting these in a map with the date so users see the most recent engagements first
            Map<LocalDateTime, Map<String, Object>> engagementInfo = new TreeMap<>(Collections.reverseOrder());
            for (ArtistEngagement e : engagements) {
                Map<String, Object> engagementMap = new HashMap<>();
                engagementMap.put("engagementId", e.getId());
                engagementMap.put("artist", e.getArtist());
                LocalDateTime dateTime = e.getEngagementDate();
                engagementInfo.put(dateTime, engagementMap);
            }

            req.setAttribute("engagementInfo", engagementInfo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("user.jsp");
            dispatcher.forward(req, resp);

        } else {
            resp.sendRedirect("login");
        }


    }

}
