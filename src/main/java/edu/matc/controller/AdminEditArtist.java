package edu.matc.controller;

import edu.matc.entity.Artist;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/edit-artist"}
)
public class AdminEditArtist extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao artistDao = new GenericDao(Artist.class);

        if (req.getParameter("submit").equals("delete")) {


        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin");
        dispatcher.forward(req, resp);

    }
}
