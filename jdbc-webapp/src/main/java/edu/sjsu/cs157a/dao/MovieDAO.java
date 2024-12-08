package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDAO {
	public MovieDAO() {

	}

	public long addMovie(Movie movie) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getDirector());
		statement.setString(3, movie.getGenre());
		statement.setInt(4, movie.getReleaseYear());
		statement.setDouble(5, movie.getRating());
		statement.setString(6, movie.getDescription());
		statement.executeUpdate();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
		if (rs.next()) {
			long primaryKey = rs.getLong(1);
			return primaryKey;
		}
		
		return 0;
	}

	public Movie getMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Movies WHERE MovieID = ?";
		Movie movie = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();

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
	
	public void updateMovie(Movie movie) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Movie SET Title = ?, Director = ?, Genre = ?, ReleaseYear = ?, Rating = ?, Description = ? WHERE MovieID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getDirector());
		statement.setString(3, movie.getGenre());
		statement.setInt(4, movie.getReleaseYear());
		statement.setDouble(5, movie.getRating());
		statement.setString(6, movie.getDescription());
		statement.executeUpdate();
	}

	public void deleteMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Movies WHERE MovieID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
