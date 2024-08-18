package com.college.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbConfig is a configuration class for managing database connections. It
 * handles the connection to a MySQL database using JDBC.
 */
public class DbConfig {

	// Database configuration information
	private static final String DB_NAME = "islington";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	/**
	 * Establishes a connection to the database.
	 *
	 * @return Connection object for the database
	 * @throws SQLException           if a database access error occurs
	 * @throws ClassNotFoundException if the JDBC driver class is not found
	 */
	public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}