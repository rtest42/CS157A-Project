package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object (DAO) class for managing database operations related to the Movie model.
 */
public class MovieDAO {
	
	/**
     * Adds a new movie record to the Movies table in the database.
     *
     * @param movie The Movie object containing the details of the movie to be added.
     * @return The primary key (MovieID) of the newly inserted movie record.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public long addMovie(Movie movie) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		// Set parameters for query
		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getDirector());
		statement.setString(3, movie.getGenre());
		statement.setInt(4, movie.getReleaseYear());
		statement.setDouble(5, movie.getRating());
		statement.setString(6, movie.getDescription());
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
     * Retrieves a movie record from the Movies table based on the provided movie ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return A Movie object containing the details of the movie, or null if no movie with the specified ID exists.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public Movie getMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Movies WHERE MovieID = ?";
		Movie movie = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();
		
		// Map result set to Movie object
		if (result.next()) {
			movie = new Movie();
			movie.setMovieID(result.getInt("MovieID"));
			movie.setTitle(result.getString("Title"));
			movie.setDirector(result.getString("Director"));
			movie.setGenre(result.getString("Genre"));
			movie.setReleaseYear(result.getInt("ReleaseYear"));
			movie.setRating(result.getDouble("Rating"));
			movie.setDescription(result.getString("Description"));
		}

		return movie;
	}
	
	/**
     * Checks if a user can rent a specific movie. A user can rent a movie if they have not already rented it.
     *
     * @param userID The ID of the user.
     * @param movieID The ID of the movie.
     * @return True if the user can rent the movie, false otherwise.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public boolean canRent(int userID, int movieID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE UserID = ? AND MovieID = ? AND Returned = FALSE";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, userID);
		statement.setInt(2, movieID);
		
		ResultSet result = statement.executeQuery();
		int rowCount = 0;
		while (result.next())
			++rowCount;
		return rowCount == 0;
	}
	
	/**
     * Checks if a user can leave a review for a specific movie. A user can review a movie if they have rented and returned it.
     *
     * @param userID The ID of the user.
     * @param movieID The ID of the movie.
     * @return True if the user can review the movie, false otherwise.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public boolean canReview(int userID, int movieID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE UserID = ? AND MovieID = ? AND Returned = TRUE";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, userID);
		statement.setInt(2, movieID);
		
		ResultSet result = statement.executeQuery();
		int rowCount = 0;
		while (result.next())
			++rowCount;
		
		if (rowCount > 0) {
			sql = "SELECT * FROM Reviews WHERE UserID = ? AND MovieID = ?";
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userID);
			statement.setInt(2, movieID);
			
			result = statement.executeQuery();
			rowCount = 0;
			
			while (result.next())
				++rowCount;
			
			return rowCount == 0;
		}
		
		return false;
	}
	
	/**
     * Retrieves all movie records from the Movies table.
     *
     * @return A list of Movie objects containing the details of all movies in the database.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public ArrayList<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Movies";
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Movie movie = new Movie();
			movie.setMovieID(result.getInt("MovieID"));
			movie.setTitle(result.getString("Title"));
			movie.setDirector(result.getString("Director"));
			movie.setGenre(result.getString("Genre"));
			movie.setReleaseYear(result.getInt("ReleaseYear"));
			movie.setRating(result.getDouble("Rating"));
			movie.setDescription(result.getString("Description"));
			movies.add(movie);
		}

		return movies;
	}

	/**
     * Deletes a movie record from the Movies table based on the provided movie ID.
     *
     * @param id The ID of the movie to delete.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public void deleteMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Movies WHERE MovieID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
