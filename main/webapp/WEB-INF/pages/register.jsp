<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<!-- Set contextPath variable for reuse -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/register.css" />
</head>
<body>
	<div class="container">
		<h1>Registration Form</h1>

		<!-- Display error message if available -->
		<c:if test="${not empty error}">
			<p class="error-message">${error}</p>
		</c:if>

		<!-- Display success message if available -->
		<c:if test="${not empty success}">
			<p class="success-message">${success}</p>
		</c:if>

		<form action="${contextPath}/register" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" value="${firstName}" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" value="${lastName}" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" value="${username}" required>
				</div>
				<div class="col">
					<label for="birthday">Date of Birth:</label> <input type="date"
						id="birthday" name="dob" value="${dob}" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male" ${gender == 'male' ? 'selected' : ''}>Male</option>
						<option value="female" ${gender == 'female' ? 'selected' : ''}>Female</option>
					</select>
				</div>
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" value="${email}" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" name="phoneNumber" value="${phoneNumber}"
						required>
				</div>
				<div class="col">
					<label for="subject">Subject:</label> <select id="subject"
						name="subject" required>
						<option value="computing"
							${subject == 'computing' ? 'selected' : ''}>Computing</option>
						<option value="multimedia"
							${subject == 'multimedia' ? 'selected' : ''}>Multimedia</option>
						<option value="networking"
							${subject == 'networking' ? 'selected' : ''}>Networking</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="image">Profile Picture:</label> <input type="file"
						id="image" name="image">
				</div>
			</div>
			<button type="submit">Register A New Account</button>
		</form>
		<a href="${contextPath}/login" class="login-button">Login If You
			Have Already An Account </a>
	</div>
</body>
</html>
