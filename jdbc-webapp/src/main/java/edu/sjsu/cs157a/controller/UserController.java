package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.UserDAO;
import edu.sjsu.cs157a.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
	private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String PROFILE = "/profile";

	private UserDAO userDAO;

	@Override
	public void init() {
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case LOGIN:
		case REGISTER:
		case PROFILE:
			String url = "/WEB-INF/views/users" + action + ".jsp";
			request.getRequestDispatcher(url).forward(request, response);
			break;
		default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested resource is not available.");
            break;
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
				response.sendRedirect(request.getContextPath() + "/users/login");
				break;
			case "/login":
				loginUser(request, response);	
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/logout":
				logoutUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			default:
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The requested resource is not available.");
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
		
		System.out.println("User registered!");
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = userDAO.getUser(email);
		// return user != null && user.getPassword().equals(password);
			if (user != null && user.getPassword().equals(password)) {
				request.getSession().setAttribute("userID", user.getUserID());
				System.out.println("User logged in!");
				response.sendRedirect(request.getContextPath() + "/movies/dashboard");
			} else {
				response.sendRedirect(request.getContextPath() + "/user/login");
			} 
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		Integer userID = (Integer) request.getSession().getAttribute("userID");
		User user = userDAO.getUser(userID);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);

		userDAO.updateUser(user);
		System.out.println("User updated profile!");
		response.sendRedirect(request.getContextPath() + "/users" + PROFILE);
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		request.getSession().invalidate();
		System.out.println("Logged out user!");
		response.sendRedirect(request.getContextPath() + "/users" + LOGIN);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		Integer userID = (Integer) request.getSession().getAttribute("userID");
		userDAO.deleteUser(userID);
		request.getSession().invalidate();
		System.out.println("Deleted user!");
		response.sendRedirect(request.getContextPath() + "/users" + LOGIN);
	}
}
