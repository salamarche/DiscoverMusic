package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/edit-artist"}
)
public class AdminEditArtistAction extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("artistDescription");
        int artistId = Integer.parseInt(req.getParameter("artistId"));

        GenericDao artistDao = new GenericDao(Artist.class);
        Artist artist = (Artist)artistDao.getById(artistId);
        artist.setDescription(description);
        artistDao.saveOrUpdate(artist);

        resp.sendRedirect("admin");

    }
}
