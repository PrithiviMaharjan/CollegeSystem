package com.collegeapp.util;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectionUtil {

	public void redirect(String msgType, String message, String redirectPage,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute(msgType, message);		
		req.getRequestDispatcher(redirectPage).forward(req, resp);

	}
}
