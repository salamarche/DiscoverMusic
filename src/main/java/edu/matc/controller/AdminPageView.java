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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Routes user to admin.jsp
 */
@WebServlet(
        urlPatterns = {"/admin"}
)
public class AdminPageView extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Controls access to admin page.
     * Checks the session for userRole admin. Forwards to admin page if ok, otherwise forwards to error page
     * with message.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao artistDao = new GenericDao(Artist.class);
        req.setAttribute("artists", artistDao.getAll());

        HttpSession session = req.getSession();
        String userRole = (String)session.getAttribute("userRole");

        String url;
        if (userRole.equals("admin")) {
            url = "/admin.jsp";

        } else {
            url = "/error.jsp";
            req.setAttribute("userMessage", "Access Denied");
        }
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        }

}
