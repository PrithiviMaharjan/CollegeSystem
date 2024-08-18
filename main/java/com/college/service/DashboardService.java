package com.college.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.college.config.DbConfig;
import com.college.model.ProgramModel;
import com.college.model.StudentModel;

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

	public StudentModel getSpecificStudentInfo(int studentId) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		// SQL query to join student and program tables
		String query = "SELECT s.student_id, s.first_name, s.last_name, s.username, s.dob, s.gender, "
				+ "s.email, s.number, s.program_id, s.image_path, "
				+ "p.name AS program_name, p.type AS program_type, p.category AS program_category " + "FROM student s "
				+ "JOIN program p ON s.program_id = p.program_id " + "WHERE s.student_id = ?";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setInt(1, studentId);
			ResultSet result = stmt.executeQuery();
			StudentModel student = null;

			if (result.next()) {
				// Extract data from the result set
				int id = result.getInt("student_id");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String userName = result.getString("username");
				LocalDate dob = result.getDate("dob").toLocalDate(); // Assuming dob is of type DATE in SQL
				String gender = result.getString("gender");
				String email = result.getString("email");
				String number = result.getString("number");
				String imageUrl = result.getString("image_path");

				// Create ProgramModel instance
				ProgramModel program = new ProgramModel();
				program.setProgramId(result.getInt("program_id"));
				program.setName(result.getString("program_name"));
				program.setType(result.getString("program_type"));
				program.setCategory(result.getString("program_category"));

				// Create StudentModel instance
				student = new StudentModel(id, firstName, lastName, userName, dob, gender, email, number, null, program,
						imageUrl);

				// Add the student to the list
			}
			return student;
		} catch (SQLException e) {
			// Log and handle exceptions
			e.printStackTrace();
			return null;
		}
	}

	public List<StudentModel> getRecentStudents() {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		// SQL query to fetch student details
		String query = "SELECT student_id, first_name, last_name, email, number "
				+ "FROM student ORDER BY student_id DESC LIMIT 3";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();
			List<StudentModel> studentList = new ArrayList<>();

			while (result.next()) {

				// Create and add StudentModel to the list
				studentList.add(new StudentModel(result.getInt("student_id"), // Student ID
						result.getString("first_name"), // First Name
						result.getString("last_name"), // Last Name
						result.getString("email"), // Email
						result.getString("number") // Phone Number
				));

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

		String updateQuery = "UPDATE student SET first_name = ?, last_name = ?, " + "username = ?, dob = ?, gender = ?,"
				+ "email = ?, number = ?, program_id = ?, image_path = ? WHERE student_id = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(updateQuery)) {
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setString(3, student.getUserName());
			stmt.setDate(4, Date.valueOf(student.getDob()));
			stmt.setString(5, student.getGender());
			stmt.setString(6, student.getEmail());
			stmt.setString(7, student.getNumber());
			stmt.setInt(8, getProgramId(student.getProgram().getName()));
			stmt.setString(9, student.getLastName());

			stmt.setInt(10, student.getId());

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

	public int getProgramId(String name) {
		if (isConnectionError)
			return -1;

		String deleteQuery = "select program_id from program where name  = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(deleteQuery)) {
			stmt.setString(1, name);

			ResultSet result = stmt.executeQuery();
			if (result.next())
				return result.getInt("program_id");
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getTotalStudents() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS total FROM student;";
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("total");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getComputingStudents() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS total FROM student WHERE program_id = 1;";
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("total");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getMultimediaStudents() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS total FROM student WHERE program_id = 2;";
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("total");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getNetworkingStudents() {
		if (isConnectionError) {
			return null;
		}

		String countQuery = "SELECT COUNT(*) AS total FROM student WHERE program_id = 3;";
		try (PreparedStatement stmt = dbConn.prepareStatement(countQuery)) {
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				return result.getString("total");
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}