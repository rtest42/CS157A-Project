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
	private static final String LIST = "/list";
	private static final String CONFIGURE = "/configure";
	
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
        String url = "/WEB-INF/views/reviews" + action + ".jsp";
		try {
			switch(action) {
        	case LIST:
        		listReviews(request, response);
        		break;
        	case CONFIGURE:
        		getReview(request, response);
        		break;
        	}
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
        	case "/review":
        		addReview(request, response);
        		break;
        	case "/update":
        		updateReview(request, response);
        		break;
        	case "/remove":
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
		// request.setAttribute("review", newReview);
		response.sendRedirect(request.getContextPath() + "/reviews/configure?id=" + primaryKey);
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
		response.sendRedirect(request.getContextPath() + "/reviews/list");
	}

	private void deleteReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		reviewDAO.deleteReview(reviewID);
		response.sendRedirect(request.getContextPath() + "/reviews/list");
	}
}
