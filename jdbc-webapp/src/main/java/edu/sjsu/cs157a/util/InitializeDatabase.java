package edu.sjsu.cs157a.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDatabase {
    private static final String FILE = "schema.sql";

    public static void main(String[] args) {
        try (Connection connection = JDBCUtil.getConnection("jdbc:mysql://localhost:3306/")) {
            // Load schema.sql from resources
            InputStream inputStream = InitializeDatabase.class.getClassLoader().getResourceAsStream(FILE);
            if (inputStream == null) {
                throw new IOException("File '" + FILE + "' not found in resources!");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            // Read the SQL script
            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line).append("\n");
            }

            // Split the SQL script into individual statements
            String[] sqlStatements = sqlBuilder.toString().split(";");

            try (Statement statement = connection.createStatement()) {
                // Execute each statement individually
                for (String sql : sqlStatements) {
                    if (!sql.trim().isEmpty()) { // Ignore empty statements
                        statement.execute(sql.trim());
                    }
                }
            }

            System.out.println("Finished initializing the database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
