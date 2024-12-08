package edu.sjsu.cs157a.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInit {
	private static final String FILE = "schema.sql";
	
	public static void main(String[] args) {
		try {
			Connection connection = DatabaseUtil.getConnection();
			
			StringBuilder sql = new StringBuilder();
			BufferedReader reader = new BufferedReader(new FileReader(FILE));
			String line;
			while ((line = reader.readLine()) != null) {
				sql.append(line).append("\n");
			}
			
			Statement statement = connection.createStatement();
			statement.execute(sql.toString());
			System.out.println("Finished initializing database.");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}
}