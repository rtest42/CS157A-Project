package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Rental;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Data Access Object (DAO) class for managing database operations related to the Rental model.
 */
public class RentalDAO {
	
	/**
     * Adds a new rental to the database and returns the generated primary key.
     *
     * @param rental The Rental object containing data to be inserted.
     * @return The generated primary key (RentalID) of the newly added rental.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If a database access error occurs.
     */
	public void addRental(Rental rental) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Rentals (MovieID, UserID, StartDate, ReturnDate, Returned) VALUES (?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// Set parameters for query
		statement.setInt(1, rental.getMovieID());
		statement.setInt(2, rental.getUserID());
		statement.setDate(3, rental.getStartDate());
		statement.setDate(4, rental.getReturnDate());
		statement.setBoolean(5, rental.isReturned());
		statement.executeUpdate();
	}
	
	/**
     * Adds a rental record with only MovieID and UserID, and returns the primary key (RentalID) of the new rental.
     *
     * @param movieID The ID of the movie being rented.
     * @param userID The ID of the user renting the movie.
     * @return The primary key (RentalID) of the new rental record.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public long addRental(int movieID, int userID) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO RENTALS (MovieID, UserID) VALUES (?, ?)";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, movieID);
		statement.setInt(2, userID);
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
     * Retrieves a rental record from the Rentals table based on the provided rental ID.
     *
     * @param id The ID of the rental record to retrieve.
     * @return A Rental object containing the details of the rental, or null if no rental with the specified ID exists.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public Rental getRental(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE RentalID = ?";
		Rental rental = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();
		
		// Map result set to Rental object
		if (result.next()) {
			rental = new Rental();
			rental.setRentalID(result.getInt("RentalID"));
			rental.setMovieID(result.getInt("MovieID"));
			rental.setUserID(result.getInt("UserID"));
			rental.setStartDate(result.getDate("StartDate"));
			rental.setReturnDate(result.getDate("ReturnDate"));
			rental.setReturned(result.getBoolean("Returned"));
		}

		return rental;
	}
	
	/**
     * Retrieves all rental records associated with a specific user ID.
     *
     * @param userID The ID of the user whose rentals are to be retrieved.
     * @return A list of Rental objects associated with the specified user.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public ArrayList<Rental> getAllRentalsFromUser(int userID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE UserID = ?";

		ArrayList<Rental> rentals = new ArrayList<Rental>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, userID);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			Rental rental = new Rental();
			rental.setRentalID(result.getInt("RentalID"));
			rental.setMovieID(result.getInt("MovieID"));
			rental.setUserID(result.getInt("UserID"));
			rental.setStartDate(result.getDate("StartDate"));
			rental.setReturnDate(result.getDate("ReturnDate"));
			rental.setReturned(result.getBoolean("Returned"));
			rentals.add(rental);
		}

		return rentals;
	}

	/**
     * Retrieves all rental records associated with a specific movie ID.
     *
     * @param movieID The ID of the movie whose rentals are to be retrieved.
     * @return A list of Rental objects associated with the specified movie.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public ArrayList<Rental> getAllRentalsByMovie(int movieID) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE MovieID = ?";

		ArrayList<Rental> rentals = new ArrayList<Rental>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, movieID);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			Rental rental = new Rental();
			rental.setRentalID(resultSet.getInt("RentalID"));
			rental.setMovieID(resultSet.getInt("MovieID"));
			rental.setUserID(resultSet.getInt("UserID"));
			rental.setStartDate(resultSet.getDate("StartDate"));
			rental.setReturnDate(resultSet.getDate("ReturnDate"));
			rental.setReturned(resultSet.getBoolean("Returned"));
			rentals.add(rental);
		}

		return rentals;
	}

	/**
     * Updates the return date and returned status for an existing rental.
     *
     * @param rental The Rental object containing the updated rental details.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     * @throws SQLException If an SQL error occurs while interacting with the database.
     */
	public void updateRental(Rental rental) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Rentals SET ReturnDate = ?, Returned = ? WHERE RentalID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setDate(1, rental.getReturnDate());
		statement.setBoolean(2, rental.isReturned());
		statement.setInt(3, rental.getRentalID());
		statement.executeUpdate();
	}
}
