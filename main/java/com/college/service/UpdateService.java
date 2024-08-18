package com.college.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.college.config.DbConfig;
import com.college.model.StudentModel;

/**
 * Service class for updating student information in the database.
 * 
 * This class provides methods to update student details and fetch program IDs
 * from the database. It manages database connections and handles SQL
 * exceptions.
 */
public class UpdateService {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public UpdateService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Updates student information in the database.
	 * 
	 * @param student The StudentModel object containing the updated student data.
	 * @return Boolean indicating the success of the update operation. Returns null
	 *         if there is a connection error or an exception occurs.
	 */
	public Boolean updateStudentInfo(StudentModel student) {
		if (isConnectionError) {
			return null;
		}

		int programId = getProgramId(student.getProgram().getName());
		if (programId == 0) {
			// Handle case where the program is not found in the database
			System.out.println("Invalid program: " + student.getProgram().getName());
			return false; // or return null if you want to handle this in the controller
		}

		String updateSQL = "UPDATE student SET first_name = ?, last_name = ?, program_id = ?, "
				+ "email = ?, number = ? WHERE student_id = ?";

		try (PreparedStatement preparedStatement = dbConn.prepareStatement(updateSQL)) {
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setInt(3, programId);
			preparedStatement.setString(4, student.getEmail());
			preparedStatement.setString(5, student.getNumber());
			preparedStatement.setInt(6, student.getId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			// Log and handle SQL exceptions
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the program ID for a given program name.
	 * 
	 * @param programName The name of the program.
	 * @return The ID of the program. Returns 0 if the program is not found or an
	 *         exception occurs.
	 */
	private int getProgramId(String programName) {
		String selectSQL = "SELECT program_id FROM program WHERE name = ?";

		try (PreparedStatement preparedStatement = dbConn.prepareStatement(selectSQL)) {
			preparedStatement.setString(1, programName);
			ResultSet result = preparedStatement.executeQuery();

			if (result.next()) {
				return result.getInt("program_id");
			}
		} catch (SQLException e) {
			// Log and handle SQL exceptions
			e.printStackTrace();
		}

		return 0;
	}
}
