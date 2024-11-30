package edu.sjsu.cs157a.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc-webapp";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}