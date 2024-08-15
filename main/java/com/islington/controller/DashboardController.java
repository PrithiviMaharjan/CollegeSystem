package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.model.ProgramModel;
import com.islington.model.StudentModel;
import com.islington.service.DashboardService;

/**
 * Servlet implementation for handling dashboard-related HTTP requests.
 * 
 * This servlet manages interactions with the DashboardService to fetch student
 * information, handle updates, and manage student data. It forwards requests to
 * appropriate JSP pages or handles POST actions based on the request parameters.
 * @author Prithivi
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/dashboard" })
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of DashboardService for handling business logic
    private DashboardService dashboardService;

    /**
     * Default constructor initializes the DashboardService instance.
     */
    public DashboardController() {
        this.dashboardService = new DashboardService();
    }

    /**
     * Handles HTTP GET requests by retrieving student information and forwarding
     * the request to the dashboard JSP page.
     * 
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException      If an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve all student information from the DashboardService
        request.setAttribute("studentList", dashboardService.getAllStudentsInfo());

        // Forward the request to the dashboard JSP for rendering
        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests for various actions such as update, delete, or 
     * redirecting to the update form. Processes the request parameters based on 
     * the specified action.
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
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @param studentId The ID of the student to be updated.
     * @throws IOException If an input or output error occurs.
     */
    private void handleUpdateForm(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int programId = Integer.parseInt(request.getParameter("programId"));
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        ProgramModel program = new ProgramModel();
        program.setProgramId(programId);
        program.setName(dashboardService.getProgramName(programId));

        StudentModel student = new StudentModel(studentId, firstName, lastName, program, email, number);

        // Store the student object in the session
        request.getSession().setAttribute("student", student);

        // Redirect to the update URL
        response.sendRedirect(request.getContextPath() + "/update");
    }

    /**
     * Handles the update action by processing student data and updating it through
     * the DashboardService. Redirects to the dashboard page upon completion.
     * 
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @param studentId The ID of the student to be updated.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException      If an input or output error occurs.
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int programId = Integer.parseInt(request.getParameter("programId"));
        String email = request.getParameter("email");
        String number = request.getParameter("number");

        ProgramModel program = new ProgramModel();
        program.setProgramId(programId);

        StudentModel student = new StudentModel(studentId, firstName, lastName, program, email, number);
        boolean success = dashboardService.updateStudent(student);

        if (success) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        // Forward to the dashboard to reflect changes
        doGet(request, response);
    }

    /**
     * Handles the delete action by removing a student from the database and
     * forwarding to the dashboard page.
     * 
     * @param request  The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
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
