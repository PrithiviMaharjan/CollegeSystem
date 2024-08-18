package com.college.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.college.config.DbConfig;
import com.college.model.StudentModel;
import com.college.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(StudentModel studentModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT username, password FROM student WHERE username = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, studentModel.getUserName());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return validatePassword(result, studentModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username and password from
	 *                     the database
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, StudentModel studentModel) throws SQLException {
		String dbUsername = result.getString("username");
		String dbPassword = result.getString("password");

		return dbUsername.equals(studentModel.getUserName())
				&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(studentModel.getPassword());
	}
}