package edu.matc.controller;

import edu.matc.entity.ArtistEngagement;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserRemoveArtistEngagement controls remove engagement form from user.jsp
 */
@WebServlet(
        urlPatterns = {"/remove-engagement"}
)
public class UserRemoveArtistEngagement extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Removes artist engagement record from a user's engagement collection.
     * Returns user to their page.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("engagementId"));

        //logger.info("remove engagement id: " + id);

        GenericDao aeDao = new GenericDao(ArtistEngagement.class);
        ArtistEngagement engagement = (ArtistEngagement) aeDao.getById(id);
        aeDao.delete(engagement);

        resp.sendRedirect("user");

    }
}
