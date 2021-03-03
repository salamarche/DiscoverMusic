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
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/editUser"}
)
public class EditUser extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        //delete a user
        if (req.getParameter("submit").equals("delete")) {
            Integer selectedUserId = Integer.parseInt(req.getParameter("selectUser"));
            User selectedUser = (User)userDao.getById(selectedUserId);
            userDao.delete(selectedUser);
        }

        //reload page
        req.setAttribute("users", userDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/users.jsp");
        dispatcher.forward(req, resp);


    }
}
