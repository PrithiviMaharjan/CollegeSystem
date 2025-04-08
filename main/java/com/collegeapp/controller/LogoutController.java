package com.collegeapp.controller;

import java.io.IOException;

import com.collegeapp.model.StudentModel;
import com.collegeapp.service.LoginService;
import com.collegeapp.util.CookiesUtil;
import com.collegeapp.util.SessionUtil;
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
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CookiesUtil.deleteCookie(resp, "role");
		SessionUtil.invalidateSession(req);
		resp.sendRedirect(RedirectionUtil.loginUrl);
	}
}