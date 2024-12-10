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
	private static final long serialVersionUID = 1L;
	
	// Constants for user paths
	private static final String USERS = "/users";
	private static final String LOGIN = "/login";
	private static final String LOGOUT = "/logout";
    private static final String REGISTER = "/register";
    private static final String PROFILE = "/profile";
    private static final String EDIT = "/edit";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";

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
		case EDIT:
			// Forward the request to the corresponding JSP page
			String url = "/WEB-INF/views" + USERS + action + ".jsp";
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
			case REGISTER:
				registerUser(request, response);
				break;
			case LOGIN:
				loginUser(request, response);	
				break;
			case UPDATE:
				updateUser(request, response);
				break;
			case LOGOUT:
				logoutUser(request, response);
				break;
			case DELETE:
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
		
		// Check if user already exists by email
		User user = userDAO.getUser(email);
		if (user != null) {
			System.out.println("Email already exists!");
			// Send GET request to specified URL
			response.sendRedirect(request.getContextPath() + USERS + REGISTER + "?status=error");
			return;
		}

		// Create a new user and add to the database
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setPhone(phone);
		newUser.setPassword(password);
		userDAO.addUser(newUser);
		
		System.out.println("User registered!");
		response.sendRedirect(request.getContextPath() + USERS + LOGIN + "?status=registered");
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Check for valid email and password
		User user = userDAO.getUser(email);
		if (user != null && user.getPassword().equals(password)) {
			request.getSession().setAttribute("user", user);
			System.out.println("User logged in!");
			response.sendRedirect(request.getContextPath() + "/movies/dashboard");
		} else {
			System.out.println("Failed attempt!");
			response.sendRedirect(request.getContextPath() + USERS + LOGIN + "?status=error");
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		
		// Get the user from session and update their details
		User user = (User) request.getSession().getAttribute("user");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);

		userDAO.updateUser(user);
		System.out.println("User updated profile!");
		response.sendRedirect(request.getContextPath() + USERS + PROFILE);
	}
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		// Clear session and redirect to login page
		request.getSession().invalidate();
		System.out.println("Logged out user!");
		response.sendRedirect(request.getContextPath() + USERS + LOGIN + "?status=logout");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		// Clear session, delete user, and redirect to login page
		User user = (User) request.getSession().getAttribute("user");
		userDAO.deleteUser(user.getUserID());
		request.getSession().invalidate();
		System.out.println("Deleted user!");
		response.sendRedirect(request.getContextPath() + USERS + LOGIN + "?status=deleted");
	}
}
