package edu.sjsu.cs157a.controller;

public class ReviewController {

}

package com.example.moviedb.controller;

import com.example.moviedb.dao.ReviewDAO;
import com.example.moviedb.model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewController extends HttpServlet {
    private ReviewDAO reviewDAO;

    @Override
    public void init() {
        reviewDAO = new ReviewDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                listReviews(request, response);
            } else if ("get".equals(action)) {
                getReview(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listReviews(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Review> reviews = reviewDAO.getAllReviews();
        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher("/review-list.jsp").forward(request, response);
    }

    private void getReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int reviewID = Integer.parseInt(request.getParameter("reviewID"));
        Review review = reviewDAO.getReviewByID(reviewID);
        if (review != null) {
            request.setAttribute("review", review);
            request.getRequestDispatcher("/review-details.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Review not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addReview(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        int movieID = Integer.parseInt(request.getParameter("movieID"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Review newReview = new Review();
        newReview.setUserID(userID);
        newReview.setMovieID(movieID);
        newReview.setRating(rating);
        newReview.setComment(comment);

        reviewDAO.addReview(newReview);
        response.sendRedirect("reviews?action=list");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateReview(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int reviewID = Integer.parseInt(request.getParameter("reviewID"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Review review = new Review();
        review.setReviewID(reviewID);
        review.setRating(rating);
        review.setComment(comment);

        reviewDAO.updateReview(review);
        response.sendRedirect("reviews?action=list");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteReview(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int reviewID = Integer.parseInt(request.getParameter("reviewID"));
        reviewDAO.deleteReview(reviewID);
        response.sendRedirect("reviews?action=list");
    }
}
