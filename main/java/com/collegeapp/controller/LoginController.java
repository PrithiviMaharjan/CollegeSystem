package com.collegeapp.controller;

import java.io.IOException;

import com.collegeapp.util.RedirectionUtil;
import com.collegeapp.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Prithivi Maharjan
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final RedirectionUtil redirectionUtil;
	private final ValidationUtil validationUtil;

	public LoginController(RedirectionUtil redirectionUtil, ValidationUtil validationUtil) {
		this.redirectionUtil = redirectionUtil;
		this.validationUtil = validationUtil;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (validationUtil.isNullOrEmpty("username") || validationUtil.isNullOrEmpty("password")) {
			redirectionUtil.redirect(req, resp,"success", "Successfully Logged In!", "/home");
		} else {
			if (username.equals("admin") && password.equals("admin")) {
				redirectionUtil.redirect(req, resp,"success", "Successfully Logged In!", "/home");
			} else {
				redirectionUtil.redirect(req, resp,"success", "Successfully Logged In!", "/home");
			}
		}

	}
}
