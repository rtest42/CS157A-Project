package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.ReviewDAO;
import edu.sjsu.cs157a.model.Review;
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

	@Override
	public void init() {
		reviewDAO = new ReviewDAO();
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
		ArrayList<Review> reviews = reviewDAO.getAllReviews();
		request.setAttribute("reviews", reviews);
		// request.getRequestDispatcher("/review/list.jsp").forward(request, response);
	}

	private void getReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("id"));
		Review review = reviewDAO.getReview(reviewID);
		request.setAttribute("review", review);
		//if (review != null) {
		//	request.setAttribute("review", review);
		//request.getRequestDispatcher("/review/details.jsp").forward(request, response);
		//} else {
		//	response.sendError(HttpServletResponse.SC_NOT_FOUND, "Review not found");
		//}
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
		Integer userID = (Integer) request.getSession().getAttribute("userID");

		Review newReview = new Review();
		newReview.setUserID(userID);
		newReview.setMovieID(movieID);
		newReview.setRating(0);
		newReview.setComment("Type your comment here...");

		reviewDAO.addReview(newReview);
		request.setAttribute("review", newReview);
		response.sendRedirect(request.getContextPath() + "/reviews/list");
	}

	private void updateReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int reviewID = Integer.parseInt(request.getParameter("reviewID"));
		int rating = Integer.parseInt(request.getParameter("rating"));
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
