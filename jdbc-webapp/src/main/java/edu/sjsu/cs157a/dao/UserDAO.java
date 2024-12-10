package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.User;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing `User` entities in the database.
 * Provides methods for CRUD operations on the `Users` table.
 */
public class UserDAO {
	
	/**
     * Adds a new user to the `Users` table.
     *
     * @param user The `User` object containing user details to be added.
     * @return The primary key (UserID) of the newly added user.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
	public long addUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Users (FirstName, LastName, Email, Phone, Password) VALUES (?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		// Set parameters for query
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPhone());
		statement.setString(5, user.getPassword());
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
     * Retrieves a user from the `Users` table by UserID.
     *
     * @param id The unique UserID of the user to retrieve.
     * @return The `User` object containing the user's details, or `null` if not found.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
	public User getUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Users WHERE UserID = ?";
		User user = null; // Default value to return if user is not found

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();
		
		// Map result set to User object
		if (result.next()) {
			user = new User();
			user.setUserID(result.getInt("UserID"));
			user.setFirstName(result.getString("FirstName"));
			user.setLastName(result.getString("LastName"));
			user.setEmail(result.getString("Email"));
			user.setPhone(result.getString("Phone"));
			user.setPassword(result.getString("Password"));
			user.setJoinDate(result.getDate("JoinDate"));
		}

		return user;
	}
	
	 /**
     * Retrieves a user from the `Users` table by email address.
     *
     * @param email The email address of the user to retrieve.
     * @return The `User` object containing the user's details, or `null` if not found.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
	public User getUser(String email) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Users WHERE Email = ?";
		User user = null;

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, email);

		ResultSet result = statement.executeQuery();

		if (result.next()) {
			user = new User();
			user.setUserID(result.getInt("UserID"));
			user.setFirstName(result.getString("FirstName"));
			user.setLastName(result.getString("LastName"));
			user.setEmail(result.getString("Email"));
			user.setPhone(result.getString("Phone"));
			user.setPassword(result.getString("Password"));
			user.setJoinDate(result.getDate("JoinDate"));
		}

		return user;
	}
	
	/**
     * Updates an existing user's details in the `Users` table.
     *
     * @param user The `User` object containing updated user details.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Password = ? WHERE UserID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPhone());
		statement.setString(5, user.getPassword());
		statement.setInt(6, user.getUserID());
		statement.executeUpdate();
	}
	
	/**
     * Deletes a user from the `Users` table by UserID.
     *
     * @param id The unique UserID of the user to delete.
     * @throws ClassNotFoundException If the JDBC driver class is not found.
     * @throws SQLException           If a database access error occurs.
     */
	public void deleteUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Users WHERE UserID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
