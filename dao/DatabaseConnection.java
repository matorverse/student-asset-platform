package com.studentassetsharing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // IMPORTANT: Update these with your actual database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/asset_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "abhiomega1"; // Change this!

    /**
     * Establishes a connection to the database.
     * @return a Connection object to the database.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Attempt to connect
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // In a real app, handle this more gracefully (e.g., show an error dialog)
        }
        return connection;
    }
}