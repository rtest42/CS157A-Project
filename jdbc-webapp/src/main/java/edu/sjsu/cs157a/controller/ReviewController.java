package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.ReviewDAO;
import edu.sjsu.cs157a.dao.MovieDAO;
import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.model.Review;
import edu.sjsu.cs157a.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/reviews/*")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Constants for review paths
	private static final String REVIEWS = "/reviews";
	private static final String LIST = "/list";
	private static final String CONFIGURE = "/configure";
	private static final String REVIEW = "/review";
	private static final String UPDATE = "/update";
	private static final String REMOVE = "/remove";
	
	private ReviewDAO reviewDAO;
	private MovieDAO movieDAO;

	@Override
	public void init() {
		reviewDAO = new ReviewDAO();
		movieDAO = new MovieDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
        String url = "/WEB-INF/views" + REVIEWS + action + ".jsp";
		try {
			switch(action) {
        	case LIST:
        		listReviews(request, response);
        		break;
        	case CONFIGURE:
        		getReview(request, response);
        		break;
        	}
			// Forward the request to the corresponding JSP page
        	request.getRequestDispatcher(url).forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listReviews(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		User user = (User) request.getSession().getAttribute("user");
		
		ArrayList<Review> reviews = reviewDAO.getReviewsFromUser(user.getUserID());
		request.setAttribute("reviews", reviews);
		
		// Get movies and map with the same index
		Movie[] movies = new Movie[reviews.size()];
    	for (int i = 0; i < movies.length; ++i) {
    		movies[i] = movieDAO.getMovie(reviews.get(i).getMovieID());
    	}
        request.setAttribute("movies", movies);
	}

	private void getReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.getReview(reviewID);
		Movie movie = movieDAO.getMovie(review.getMovieID());
		request.setAttribute("review", review);
		request.setAttribute("movie", movie);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			switch (action) {
        	case REVIEW:
        		addReview(request, response);
        		break;
        	case UPDATE:
        		updateReview(request, response);
        		break;
        	case REMOVE:
        		deleteReview(request, response);
        		break;
        	}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		User user = (User) request.getSession().getAttribute("user");

		Review newReview = new Review();
		newReview.setUserID(user.getUserID());
		newReview.setMovieID(movieID);
		// Default values
		newReview.setRating(1.0);
		newReview.setComment("Type your comment here...");

		long primaryKey = reviewDAO.addReview(newReview);
		// Send GET request to specified URL
		response.sendRedirect(request.getContextPath() + REVIEWS + CONFIGURE + "?id=" + primaryKey);
	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		double rating = Double.parseDouble(request.getParameter("rating"));
		String comment = request.getParameter("comment");

		Review review = reviewDAO.getReview(reviewID);
		review.setRating(rating);
		review.setComment(comment);

		reviewDAO.updateReview(review);
		response.sendRedirect(request.getContextPath() + REVIEWS + LIST);
	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		reviewDAO.deleteReview(reviewID);
		response.sendRedirect(request.getContextPath() + REVIEWS + LIST);
	}
}
