<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login to your account</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/login.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/validation.css" />
</head>
<body>
	<div class="login-box">
		<jsp:include page="validation.jsp" />

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
			<div class="row">
				<button type="submit" class="login-button">Login</button>
			</div>
		</form>

		<!-- Forgot Password and Register Button in Separate Rows -->
		<div class="row">
			<a href="${contextPath}/forgotpassword" class="forgot-button">Forgot
				Password</a>
		</div>

		<div class="row">
			<a href="${contextPath}/register" class="register-button">Create
				new account</a>
		</div>
	</div>
</body>
</html>
