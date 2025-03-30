package com.collegeapp.util;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Prithivi Maharjan
 */
public class RedirectionUtil {

	public void redirect(HttpServletRequest req, HttpServletResponse resp, String msgType, String message,
			String redirectPage) throws ServletException, IOException {
		req.setAttribute(msgType, message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);

	}
}