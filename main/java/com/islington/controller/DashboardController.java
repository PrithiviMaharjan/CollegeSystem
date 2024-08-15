package com.islington.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.islington.service.DashboardService;

/**
 * Servlet implementation class DashboardController
 * 
 * This servlet handles HTTP requests for the dashboard page of the application.
 * It interacts with the DashboardService to retrieve data about students and forwards
 * the request to the appropriate JSP page for rendering.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Instance of DashboardService to handle business logic related to dashboards
    private DashboardService dashboardService;

    /**
     * Default constructor that initializes the DashboardService instance.
     */
    public DashboardController() {
        this.dashboardService = new DashboardService();
    }

    /**
     * Handles HTTP GET requests by retrieving student information from the DashboardService
     * and forwarding the request to the dashboard JSP page.
     * 
     * @param request The HttpServletRequest object containing the request data.
     * @param response The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all student information from the DashboardService
        request.setAttribute("studentList", dashboardService.getAllStudentsInfo());

        // Forward the request to the dashboard JSP for rendering
        request.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp").forward(request, response);
    }

    /**
     * Handles HTTP POST requests. Currently, this method is not implemented and
     * simply calls the superclass method.
     * 
     * @param req The HttpServletRequest object containing the request data.
     * @param resp The HttpServletResponse object used to return the response.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an input or output error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Implement POST request handling logic
        super.doPost(req, resp);
    }
}
