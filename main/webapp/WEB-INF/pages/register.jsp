<%@ page import="com.collegeapp.model.ProgramModel"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/register.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/validation.css" />
</head>
<body>
	<div class="container">
		<h1>Registration Form</h1>
		<jsp:include page="validation.jsp" />

		<form action="${contextPath}/register" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" name="firstName" value="Prithivi" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" name="lastName" value="Maharjan" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="username" name="username" value="Prithivi@12" required>
				</div>
				<div class="col">
					<label for="birthday">Date of Birth:</label> <input type="date"
						id="birthday" name="dob" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" value="Prithivi@gmail.com" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" name="phoneNumber" value="9878787656" required>
				</div>
				<div class="col">
					<label for="subject">Subject:</label> <select id="subject"
						name="subject" required>
						<option value="">Select a program</option>
						<c:forEach var="program" items="${programs}">
							<option value="${program.id}">${program.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" value="Prithivi@12" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword"
						value="Prithivi@12" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="image">Profile Picture</label> <input type="file"
						id="image" name="image">
				</div>
			</div>
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>
