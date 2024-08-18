package com.college.controller.admin;

import java.io.IOException;

import com.college.model.ProgramModel;
import com.college.model.StudentModel;
import com.college.service.UpdateService;
import com.college.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation for handling student update operations.
 * 
 * This servlet processes HTTP requests for updating student information.
 * It interacts with the UpdateService to perform database operations and 
 * forwards requests to the appropriate JSP page for user interaction.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/studentUpdate" })
public class UpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Service for updating student information
    private UpdateService updateService;

    /**
     * Default constructor initializes the UpdateService instance.
     */
    public UpdateController() {
        this.updateService = new UpdateService();
    }

    /**
     * Handles HTTP GET requests by retrieving student information from the session 
     * and forwarding the request to the update JSP page.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Retrieve and set student information from the session if available
        if (req.getSession().getAttribute("student") != null) {
            StudentModel student = (StudentModel) SessionUtil.getAttribute(req, "student");
            SessionUtil.removeAttribute(req, "student");
            req.setAttribute("student", student);
        }

        // Forward to the update JSP page
        req.getRequestDispatcher("/WEB-INF/pages/admin/update.jsp").forward(req, resp);
    }

    /**
     * Handles HTTP POST requests for updating student information.
     * Retrieves student data from the request parameters, updates the student record 
     * in the database using UpdateService, and redirects to the dashboard or 
     * handles update failure.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Retrieve student data from request parameters
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String number = req.getParameter("number");

        // Create ProgramModel object
        ProgramModel program = new ProgramModel();
        program.setName(req.getParameter("subject"));

        // Create StudentModel object with updated data
        StudentModel student = new StudentModel(studentId, firstName, 
                lastName, program, email, number);

        // Attempt to update student information in the database
        Boolean result = updateService.updateStudentInfo(student);
        if (result != null && result) {
            resp.sendRedirect(req.getContextPath() + "/modifyStudents"); // Redirect to dashboard on success
        } else {
            req.getSession().setAttribute("student", student);
            handleUpdateFailure(req, resp, result); // Handle failure
        }
    }

    /**
     * Handles update failures by setting an error message and forwarding the request 
     * back to the update page.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @param loginStatus Indicates the result of the update operation.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    private void handleUpdateFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        // Determine error message based on update result
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "Update Failed. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher(req.getContextPath() + "/update").forward(req, resp);
    }
}