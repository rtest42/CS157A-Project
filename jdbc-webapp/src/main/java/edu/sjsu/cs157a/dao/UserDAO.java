package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.User;
import edu.sjsu.cs157a.util.DatabaseUtil;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public UserDAO() {

	}

	public void addUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Users (FirstName, LastName, Email, Phone, Password, JoinDate) VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPhone());
		statement.setString(5, user.getPassword());
		statement.setDate(6, user.getJoinDate());
		statement.executeUpdate();
	}

	public User getUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Users WHERE UserID = ?";
		User user = null;

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);

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
	
	public ArrayList<User> getAllUsers() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Users";
		
		ArrayList<User> users = new ArrayList<User>();
		
		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			User user = new User();
			user.setUserID(result.getInt("UserID"));
			user.setFirstName(result.getString("FirstName"));
			user.setLastName(result.getString("LastName"));
			user.setEmail(result.getString("Email"));
			user.setPhone(result.getString("Phone"));
			user.setPassword(result.getString("Password"));
			user.setJoinDate(result.getDate("JoinDate"));
			users.add(user);
		}

		return users;
	}

	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Password = ? WHERE UserID = ?";

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPhone());
		statement.setString(5, user.getPassword());
		statement.setInt(6, user.getUserID());
		statement.executeUpdate();
	}

	public void deleteUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Users WHERE UserID = ?";

		Connection connection = DatabaseUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
