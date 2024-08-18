<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login to your account</title>
<!-- Set contextPath variable for reuse -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/login.css" />
</head>
<body>
	<div class="login-box">
		<h2>Login</h2>
		<form action="${contextPath}/login" method="post">
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
			</div>
			<button type="submit" class="login-button">Login To Your
				Account</button>
		</form>
		<a href="${contextPath}/register">Register Your New Account</a>

		<!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>
	</div>
</body>
</html>
