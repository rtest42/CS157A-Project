package edu.sjsu.cs157a.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc-webapp"; // Edit default URL here
    private static final String USERNAME = "root"; // Edit user name here
    private static final String PASSWORD = "password"; // Edit password here

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
    	// Load JDBC class into memory
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static Connection getConnection(String url) throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }
}