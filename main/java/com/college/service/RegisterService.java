package com.college.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.college.config.DbConfig;
import com.college.model.StudentModel;

/**
 * RegisterService handles the registration of new students. It manages database
 * interactions for student registration.
 */
public class RegisterService {

	private Connection dbConn;

	/**
	 * Constructor initializes the database connection.
	 */
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Registers a new student in the database.
	 *
	 * @param studentModel the student details to be registered
	 * @return Boolean indicating the success of the operation
	 */
	public Boolean addStudent(StudentModel studentModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String programQuery = "SELECT program_id FROM program WHERE name = ?";
		String insertQuery = "INSERT INTO student (first_name, last_name, username, dob, gender, email, number, password, program_id, image_path) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement programStmt = dbConn.prepareStatement(programQuery);
				PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {

			// Fetch program ID
			programStmt.setString(1, studentModel.getProgram().getName());
			ResultSet result = programStmt.executeQuery();
			int programId = result.next() ? result.getInt("program_id") : 1;

			// Insert student details
			insertStmt.setString(1, studentModel.getFirstName());
			insertStmt.setString(2, studentModel.getLastName());
			insertStmt.setString(3, studentModel.getUserName());
			insertStmt.setDate(4, Date.valueOf(studentModel.getDob()));
			insertStmt.setString(5, studentModel.getGender());
			insertStmt.setString(6, studentModel.getEmail());
			insertStmt.setString(7, studentModel.getNumber());
			insertStmt.setString(8, studentModel.getPassword());
			insertStmt.setInt(9, programId);
			insertStmt.setString(10, studentModel.getImageUrl());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
