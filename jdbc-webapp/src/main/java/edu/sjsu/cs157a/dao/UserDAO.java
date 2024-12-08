package edu.sjsu.cs157a.dao;

import edu.sjsu.cs157a.model.User;
import edu.sjsu.cs157a.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	public UserDAO() {

	}

	public long addUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Users (FirstName, LastName, Email, Phone, Password) VALUES (?, ?, ?, ?, ?)";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getPhone());
		statement.setString(5, user.getPassword());
		statement.executeUpdate();
		
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID()");
		if (rs.next()) {
			long primaryKey = rs.getLong(1);
			return primaryKey;
		}
		
		return 0;
	}

	public User getUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Users WHERE UserID = ?";
		User user = null;

		Connection connection = JDBCUtil.getConnection();
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

	public void deleteUser(int id) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Users WHERE UserID = ?";

		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setInt(1, id);
		statement.executeUpdate();
	}
}
