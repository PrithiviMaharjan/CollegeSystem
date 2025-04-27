package com.collegeapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.collegeapp.config.DbConfig;
import com.collegeapp.model.StudentModel;
import com.collegeapp.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class DashboardService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public DashboardService() {
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
	public Integer getCourseStudentCount(String course) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "select count(*) as total_student from student s join program p on s.program_id = p.id where lower(p.name) = lower(?)";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, course);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt("total_student");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Integer getTotalStudentCount() {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "select count(*) as total_student from student";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt("total_student");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<StudentModel> getRecentlyEnrolledStudents() {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "select first_name, last_name, username, email, number from student order by created_at desc limit 5";

		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			ResultSet result = stmt.executeQuery();

			List<StudentModel> studentList = new ArrayList<StudentModel>();
			while(result.next()) {
				studentList.add(new StudentModel(result.getString("first_name"), result.getString("last_name"),
						result.getString("username"), result.getString("email"), result.getString("number")));
			}
			
			return studentList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}