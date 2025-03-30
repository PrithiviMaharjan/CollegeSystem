package com.collegeapp.controller;

import java.io.IOException;

import com.collegeapp.util.RedirectionUtil;
import com.collegeapp.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = "/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final RedirectionUtil redirectionUtil;

	public LoginController(RedirectionUtil redirectionUtil) {
		this.redirectionUtil = redirectionUtil;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String message = "";

		if (ValidationUtil.isNullOrEmpty("username") || ValidationUtil.isNullOrEmpty("password")) {
			// message
			redirectionUtil.redirect("success", "Successfully Logged In!", "/home", req, resp);
		} else {
			if (username.equals("admin") && password.equals("admin")) {
				redirectionUtil.redirect("success", "Successfully Logged In!", "/home", req, resp);
				// redirect home page
			} else {
				// message
				redirectionUtil.redirect("success", "Successfully Logged In!", "/home", req, resp);
			}
		}

	}

}
