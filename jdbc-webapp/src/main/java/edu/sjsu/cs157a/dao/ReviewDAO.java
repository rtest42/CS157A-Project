package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Review;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAO {
	public ReviewDAO() {
		
	}

	public void addReview(Review review) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Reviews (UserID, MovieID, Rating, Comment) VALUES (?, ?, ?, ?)";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, review.getUserID());
		statement.setInt(2, review.getMovieID());
		statement.setDouble(3, review.getRating());
		statement.setString(4, review.getComment());
		statement.executeUpdate();
	}

	public Review getReview(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Reviews WHERE ReviewID = ?";
		Review review = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			review = new Review();
			review.setReviewID(resultSet.getInt("ReviewID"));
			review.setUserID(resultSet.getInt("UserID"));
			review.setMovieID(resultSet.getInt("MovieID"));
			review.setRating(resultSet.getDouble("Rating"));
			review.setComment(resultSet.getString("Comment"));
		}

		return review;
	}
	
	public ArrayList<Review> getAllReviews() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Reviews";
		ArrayList<Review> reviews = new ArrayList<Review>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Review review = new Review();
			review.setReviewID(result.getInt("ReviewID"));
			review.setUserID(result.getInt("UserID"));
			review.setMovieID(result.getInt("MovieID"));
			review.setRating(result.getDouble("Rating"));
			review.setComment(result.getString("Comment"));
			reviews.add(review);
		}

		return reviews;
	}

	public ArrayList<Review> getReviewsByMovie(int movieID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Reviews WHERE MovieID = ?";
		ArrayList<Review> reviews = new ArrayList<Review>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, movieID);
		
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Review review = new Review();
			review.setReviewID(result.getInt("ReviewID"));
			review.setUserID(result.getInt("UserID"));
			review.setMovieID(result.getInt("MovieID"));
			review.setRating(result.getDouble("Rating"));
			review.setComment(result.getString("Comment"));
			reviews.add(review);
		}

		return reviews;
	}

	public ArrayList<Review> getReviewsFromUser(int userID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Reviews WHERE UserID = ?";
		ArrayList<Review> reviews = new ArrayList<Review>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, userID);
		
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Review review = new Review();
			review.setReviewID(resultSet.getInt("ReviewID"));
			review.setUserID(resultSet.getInt("UserID"));
			review.setMovieID(resultSet.getInt("MovieID"));
			review.setRating(resultSet.getDouble("Rating"));
			review.setComment(resultSet.getString("Comment"));
			reviews.add(review);
		}

		return reviews;
	}

	public void updateReview(Review review) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Reviews SET Rating = ?, Comment = ? WHERE ReviewID = ?";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setDouble(1, review.getRating());
		statement.setString(2, review.getComment());
		statement.setInt(3, review.getReviewID());
		statement.executeUpdate();

	}

	public void deleteReview(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Reviews WHERE ReviewID = ?";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, id);
		statement.executeUpdate();

	}
}
