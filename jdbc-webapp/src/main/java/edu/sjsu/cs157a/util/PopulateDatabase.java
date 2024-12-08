package edu.sjsu.cs157a.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PopulateDatabase {
	private static final String FILE = "movies.csv";
	
	public static void main(String[] args) {
		String sql = "INSERT INTO Movies (Title, Director, Genre, ReleaseYear, Rating, Description) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = JDBCUtil.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			InputStream inputStream = InitializeDatabase.class.getClassLoader().getResourceAsStream(FILE);
			String line;
			int count = 0;
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
				
				connection.setAutoCommit(false);
				
				reader.readLine(); // Skip header
				
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					String title = data[0].trim();
					String director = data[1].trim();
					String genre = data[2].trim();
					int releaseYear = Integer.parseInt(data[3].trim());
					double rating = Double.parseDouble(data[4].trim());
					String description = data[5].trim();
					
					// Remove quotes if fields contain quotes
					title = title.replace("\"", "");
					director = director.replace("\"", "");
					genre = genre.replace("\"", "");
					description = description.replace("\"", "");
					
					statement.setString(1, title);
					statement.setString(2, director);
					statement.setString(3, genre);
					statement.setInt(4, releaseYear);
					statement.setDouble(5, rating);
					statement.setString(6, description);
					
					statement.addBatch();
					
					++count;
				}
			}
			statement.executeBatch();
			connection.commit();
			System.out.println("Read and executed " + count + " lines.");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}
}
