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
import java.util.ArrayList;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			switch(action) {
			case "/profile":
				getUser(request, response);
				break;
			case "/list":
				listUsers(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		ArrayList<User> users = userDAO.getAllUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/users/list.jsp").forward(request, response);
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		User user = userDAO.getUser(userID);

		if (user != null) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/profile.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		try {
			switch (path) {
			case "/register":
				registerUser(request, response);
				break;
			case "/login":
				loginUser(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
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

		response.sendRedirect("/users/login.jsp");
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			User user = userDAO.getUser(email);
			if (user != null && user.getPassword().equals(password)) {
				request.getSession().setAttribute("loggedInUser", user);
				response.sendRedirect("/movies/dashboard.jsp");
			} else {
				response.sendRedirect("/user/login.jsp?error=Invalid email or password");
			}
		} catch (ClassNotFoundException e) { 
			// TODO
			e.printStackTrace();
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			updateUser(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
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
		response.sendRedirect("/users/profile.jsp");
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			deleteUser(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO auto-generated
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int userID = Integer.parseInt(request.getParameter("userID"));
		userDAO.deleteUser(userID);
		response.sendRedirect("/users/login.jsp");
	}
}
