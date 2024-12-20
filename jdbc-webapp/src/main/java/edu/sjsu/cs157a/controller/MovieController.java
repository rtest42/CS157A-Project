package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.MovieDAO;
import edu.sjsu.cs157a.dao.ReviewDAO;
import edu.sjsu.cs157a.dao.UserDAO;
import edu.sjsu.cs157a.model.Review;
import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/movies/*")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Constants for movie paths
	private static final String MOVIES = "/movies";
	private static final String DASHBOARD = "/dashboard";
	private static final String DETAILS = "/details";
	
	private MovieDAO movieDAO;
	private ReviewDAO reviewDAO;
	private UserDAO userDAO;

	@Override
	public void init() {
		movieDAO = new MovieDAO();
		reviewDAO = new ReviewDAO();
		userDAO = new UserDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		String url = "/WEB-INF/views" + MOVIES + action + ".jsp";
		try {
			switch (action) {
			case DASHBOARD:
				listMovies(request, response);
				break;
			case DETAILS:
				getMovie(request, response);
				break;
			}
			// Forward the request to the corresponding JSP page
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listMovies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		ArrayList<Movie> movies = movieDAO.getAllMovies();
		request.setAttribute("movies", movies);
	}
	
	private void getMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int movieID = Integer.parseInt(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		
		Movie movie = movieDAO.getMovie(movieID);
		request.setAttribute("movie", movie);
		
		// Default value if user is a guest
		boolean canRent = false;
		boolean canReview = false;
		if (user != null) {
			canRent = movieDAO.canRent(user.getUserID(), movieID);
			canReview = movieDAO.canReview(user.getUserID(), movieID);
		}
		request.setAttribute("canRent", canRent);
		request.setAttribute("canReview", canReview);
		
		ArrayList<Review> reviews = reviewDAO.getReviewsByMovie(movieID);
		request.setAttribute("reviews", reviews);
		
		// Get users and map with the same index
		User[] users = new User[reviews.size()];
		for (int i = 0; i < users.length; ++i) {
			users[i] = userDAO.getUser(reviews.get(i).getUserID());
		}
		
		request.setAttribute("users", users);
	}
}
