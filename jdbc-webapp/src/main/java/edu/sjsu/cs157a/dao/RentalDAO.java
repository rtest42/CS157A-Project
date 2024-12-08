package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.Rental;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalDAO {
	public RentalDAO() {

	}

	public void addRental(Rental rental) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Rentals (MovieID, UserID, StartDate, ReturnDate, Returned) VALUES (?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, rental.getMovieID());
		statement.setInt(2, rental.getUserID());
		statement.setDate(3, rental.getStartDate());
		statement.setDate(4, rental.getReturnDate());
		statement.setBoolean(5, rental.isReturned());
		statement.executeUpdate();
	}
	
	public void addRental(int movieID, int userID) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO RENTALS (MovieID, UserID) VALUES (?, ?)";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setInt(1, movieID);
		statement.setInt(2, userID);
		statement.executeUpdate();
	}

	public Rental getRental(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals WHERE RentalID = ?";
		Rental rental = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();

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
	
	public ArrayList<Rental> getAllRentals() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Rentals";

		ArrayList<Rental> rentals = new ArrayList<Rental>();

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

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

	public void updateRental(Rental rental) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Rentals SET ReturnDate = ?, Returned = ? WHERE RentalID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setDate(1, rental.getReturnDate());
		statement.setBoolean(2, rental.isReturned());
		statement.setInt(3, rental.getRentalID());
		statement.executeUpdate();
	}

	public void deleteRental(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Rentals WHERE RentalID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
