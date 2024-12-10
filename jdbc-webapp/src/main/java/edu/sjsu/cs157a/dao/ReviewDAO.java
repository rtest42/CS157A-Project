package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Review;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) class for managing database operations related to the Review model.
 */
public class ReviewDAO {
	
	/**
     * Adds a new review to the database and returns the generated primary key.
     *
     * @param review The Review object containing data to be inserted.
     * @return The generated primary key (ReviewID) of the newly added review.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
	public long addReview(Review review) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Reviews (UserID, MovieID, Rating, Comment) VALUES (?, ?, ?, ?)";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// Set parameters for query
		statement.setInt(1, review.getUserID());
		statement.setInt(2, review.getMovieID());
		statement.setDouble(3, review.getRating());
		statement.setString(4, review.getComment());
		statement.executeUpdate();

		// Retrieve generated primary key
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
		if (rs.next()) {
			long primaryKey = rs.getLong(1);
			return primaryKey;
		}
		
		return 0; // Default value; should not return this
	}

	/**
     * Retrieves a specific review from the database by its ID.
     *
     * @param id The ID of the review to retrieve.
     * @return A Review object representing the retrieved review, or null if not found.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
	public Review getReview(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Reviews WHERE ReviewID = ?";
		Review review = null; // Default value to return if review is not found

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		// Map result set to Review object
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
	
	/**
     * Retrieves all reviews for a specific movie from the database.
     *
     * @param movieID The ID of the movie whose reviews are to be retrieved.
     * @return An ArrayList of Review objects representing the reviews.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
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
	
	/**
     * Retrieves all reviews from a specific user from the database.
     *
     * @param userID The ID of the user whose reviews are to be retrieved.
     * @return An ArrayList of Review objects representing the reviews.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
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
	
	/**
     * Updates an existing review in the database.
     *
     * @param review The Review object containing updated data.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
	public void updateReview(Review review) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Reviews SET Rating = ?, Comment = ? WHERE ReviewID = ?";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setDouble(1, review.getRating());
		statement.setString(2, review.getComment());
		statement.setInt(3, review.getReviewID());
		statement.executeUpdate();

	}

	/**
     * Deletes a specific review from the database by its ID.
     *
     * @param id The ID of the review to delete.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
	public void deleteReview(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Reviews WHERE ReviewID = ?";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, id);
		statement.executeUpdate();

	}
}
