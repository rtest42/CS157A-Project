package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.UserDAO;
import edu.sjsu.cs157a.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UserController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                listUsers(request, response);
            } else if ("get".equals(action)) {
                getUser(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        User user = userDAO.getUserByID(userID);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/user-details.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                createUser(request, response);
            } else if ("login".equals(action)) {
                loginUser(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        userDAO.addUser(newUser);

        response.sendRedirect("users?action=list");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid email or password");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateUser(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserID(userID);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        userDAO.updateUser(user);
        response.sendRedirect("users?action=list");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteUser(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        userDAO.deleteUser(userID);
        response.sendRedirect("users?action=list");
    }
}
