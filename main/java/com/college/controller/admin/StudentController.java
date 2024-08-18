package com.college.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import com.college.model.ProgramModel;
import com.college.model.StudentModel;
import com.college.service.DashboardService;
import com.college.util.ValidationUtil;

/**
 * Servlet implementation class StudentController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/modifyStudents" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Instance of DashboardService for handling business logic
	private DashboardService dashboardService;

	/**
	 * Default constructor initializes the DashboardService instance.
	 */
	public StudentController() {
		this.dashboardService = new DashboardService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve all student information from the DashboardService
		request.setAttribute("studentList", dashboardService.getAllStudentsInfo());

		request.setAttribute("total", dashboardService.getTotalStudents());
		request.setAttribute("computing", dashboardService.getComputingStudents());
		request.setAttribute("multimedia", dashboardService.getMultimediaStudents());
		request.setAttribute("networking", dashboardService.getNetworkingStudents());
		// Forward the request to the students JSP for rendering
		request.getRequestDispatcher("/WEB-INF/pages/admin/students.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests for various actions such as update, delete, or
	 * redirecting to the update form. Processes the request parameters based on the
	 * specified action.
	 * 
	 * @param request  The HttpServletRequest object containing the request data.
	 * @param response The HttpServletResponse object used to return the response.
	 * @throws ServletException If an error occurs during request processing.
	 * @throws IOException      If an input or output error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		int studentId = Integer.parseInt(request.getParameter("studentId"));

		switch (action) {
		case "updateForm":
			handleUpdateForm(request, response, studentId);
			break;

		case "update":
			handleUpdate(request, response, studentId);
			break;

		case "delete":
			handleDelete(request, response, studentId);
			break;

		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
		}
	}

	/**
	 * Handles the update form action by setting student data in the session and
	 * redirecting to the update page.
	 * 
	 * @param request   The HttpServletRequest object containing the request data.
	 * @param response  The HttpServletResponse object used to return the response.
	 * @param studentId The ID of the student to be updated.
	 * @throws IOException If an input or output error occurs.
	 */
	private void handleUpdateForm(HttpServletRequest request, HttpServletResponse response, int studentId)
			throws ServletException, IOException {
		// Retrieve the student information from the service
		StudentModel student = dashboardService.getSpecificStudentInfo(studentId);

		if (student != null) {
			// Store the student object in the session
			request.getSession().setAttribute("student", student);

			// Redirect to the update URL
			response.sendRedirect(request.getContextPath() + "/studentUpdate");
		} else {
			// Handle case where student info is not found
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found with ID: " + studentId);
		}
	}

	/**
	 * Handles the update action by processing student data and updating it through
	 * the DashboardService. Redirects to the dashboard page upon completion.
	 * 
	 * @param request   The HttpServletRequest object containing the request data.
	 * @param response  The HttpServletResponse object used to return the response.
	 * @param studentId The ID of the student to be updated.
	 * @throws ServletException If an error occurs during request processing.
	 * @throws IOException      If an input or output error occurs.
	 */
	private void handleUpdate(HttpServletRequest request, HttpServletResponse response, int studentId)
			throws ServletException, IOException {
		// Validate form parameters
		String validationMessage = validateUpdateForm(request);
		if (validationMessage != null) {
			request.setAttribute("error", validationMessage);
			doGet(request, response); // Reload the page with the error message
			return;
		}

		// Retrieve form parameters
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("username");
		String dobStr = request.getParameter("dob");
		LocalDate dob = LocalDate.parse(dobStr); // Convert date string to LocalDate
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String number = request.getParameter("phoneNumber");
		String programName = request.getParameter("subject");
		String imageUrl = request.getParameter("image"); // Assuming image upload is handled elsewhere

		// Create a ProgramModel object
		ProgramModel program = new ProgramModel();
		program.setName(programName);

		// Create a StudentModel object
		StudentModel student = new StudentModel(studentId, firstName, lastName, userName, dob, gender, email, number,
				null, program, imageUrl);

		// Update the student using DashboardService
		boolean success = dashboardService.updateStudent(student);

		// Handle the result of the update operation
		if (success) {
			request.setAttribute("success", "Student information updated successfully.");
		} else {
			request.setAttribute("error", "Failed to update student information.");
		}

		// Forward to the dashboard to reflect changes
		doGet(request, response);
	}

	/**
	 * Validates the form fields for the update operation.
	 * 
	 * @param request The HttpServletRequest object containing the request data.
	 * @return A validation error message, or null if all validations pass.
	 */
	private String validateUpdateForm(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String dobStr = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String number = request.getParameter("phoneNumber");
		String subject = request.getParameter("subject");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "Username is required.";
		if (ValidationUtil.isNullOrEmpty(dobStr))
			return "Date of birth is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(number))
			return "Phone number is required.";
		if (ValidationUtil.isNullOrEmpty(subject))
			return "Subject is required.";

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPhoneNumber(number))
			return "Phone number must be 10 digits and start with 98.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old.";

		// Assuming image validation is handled elsewhere
		// return null if all validations pass
		return null;
	}

	/**
	 * Handles the delete action by removing a student from the database and
	 * forwarding to the dashboard page.
	 * 
	 * @param request   The HttpServletRequest object containing the request data.
	 * @param response  The HttpServletResponse object used to return the response.
	 * @param studentId The ID of the student to be deleted.
	 * @throws ServletException If an error occurs during request processing.
	 * @throws IOException      If an input or output error occurs.
	 */
	private void handleDelete(HttpServletRequest request, HttpServletResponse response, int studentId)
			throws ServletException, IOException {
		boolean success = dashboardService.deleteStudent(studentId);

		if (success) {
			System.out.println("Deletion successful");
		} else {
			System.out.println("Deletion failed");
		}

		// Forward to the dashboard to reflect changes
		doGet(request, response);
	}
}
