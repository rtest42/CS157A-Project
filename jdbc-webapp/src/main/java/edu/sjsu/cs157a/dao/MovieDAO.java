package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.util.DatabaseUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {
	public MovieDAO() {

	}

	public void addMovie(Movie movie) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getDirector());
		statement.setString(3, movie.getGenre());
		statement.setInt(4, movie.getReleaseYear());
		statement.setDouble(5, movie.getRating());
		statement.setString(6, movie.getDescription());
		statement.executeUpdate();
	}

	public Movie getMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Movies WHERE MovieID = ?";
		Movie movie = null;

		Connection connection = DatabaseUtil.getConnection();
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
	
	public ArrayList<Movie> getAllMovies() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Movies";
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		Connection connection = DatabaseUtil.getConnection();
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

	public void deleteMovie(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Movies WHERE MovieID = ?";

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
