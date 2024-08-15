package com.islington.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.islington.config.DbConfig;
import com.islington.model.ProgramModel;
import com.islington.model.StudentModel;

/**
 * Service class for interacting with the database to retrieve dashboard-related
 * data. This class handles database connections and performs queries to fetch
 * student information.
 */
public class DashboardService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor that initializes the database connection. Sets the connection
	 * error flag if the connection fails.
	 */
	public DashboardService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			// Log and handle exceptions related to database connection
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Retrieves all student information from the database.
	 * 
	 * @return A list of StudentModel objects containing student data. Returns null
	 *         if there is a connection error or if an exception occurs during query
	 *         execution.
	 */
	public List<StudentModel> getAllStudentsInfo() {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		// SQL query to fetch student details
		String query = "SELECT student_id, first_name, last_name, program_id, email, number FROM student";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();
			List<StudentModel> studentList = new ArrayList<>();

			while (result.next()) {
				// SQL query to fetch program name based on program_id
				String programQuery = "SELECT program_id, name FROM program WHERE program_id = ?";
				try (PreparedStatement programStmt = dbConn.prepareStatement(programQuery)) {
					programStmt.setInt(1, result.getInt("program_id"));
					ResultSet programResult = programStmt.executeQuery();

					ProgramModel programModel = new ProgramModel();
					if (programResult.next()) {
						// Set program name in the ProgramModel
						programModel.setName(programResult.getString("name"));
						programModel.setProgramId(programResult.getInt("program_id"));
					}

					// Create and add StudentModel to the list
					studentList.add(new StudentModel(result.getInt("student_id"), // Student ID
							result.getString("first_name"), // First Name
							result.getString("last_name"), // Last Name
							programModel, // Associated Program
							result.getString("email"), // Email
							result.getString("number") // Phone Number
					));

					programResult.close(); // Close ResultSet to avoid resource leaks
				} catch (SQLException e) {
					// Log and handle exceptions related to program query execution
					e.printStackTrace();
					// Continue to process other students or handle this error appropriately
				}
			}
			return studentList;
		} catch (SQLException e) {
			// Log and handle exceptions related to student query execution
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateStudent(StudentModel student) {
		if (isConnectionError)
			return false;

		String updateQuery = "UPDATE student SET first_name = ?, last_name = ?, program_id = ?, email = ?, number = ? WHERE student_id = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setInt(3, student.getProgram().getProgramId());
			stmt.setString(4, student.getEmail());
			stmt.setString(5, student.getNumber());
			stmt.setInt(6, student.getId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudent(int studentId) {
		if (isConnectionError)
			return false;

		String deleteQuery = "DELETE FROM student WHERE student_id = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
			stmt.setInt(1, studentId);

			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getProgramName(int id) {
		if (isConnectionError)
			return null;

		String deleteQuery = "select name from program where program_id = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();
			if (result.next())
				return result.getString("name");
			else
				return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}