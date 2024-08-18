<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="student" value="${requestScope.student}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/update.css" />
</head>
<body>

	<div class="container">
		<div class="sidebar">
			<ul class="nav">
				<li><a href="${contextPath}/dashboard"><span class="icon">🏠</span>
						Dashboard</a></li>
				<li><a href="${contextPath}/adminOrder"><span class="icon">📊</span>
						Order List</a></li>
				<li><a href="${contextPath}/modifyStudents"><span
						class="icon">💳</span> Students</a></li>
				<li><a href="${contextPath}/studentUpdate"><span
						class="icon">✏️</span> Update Student</a></li>
			</ul>
			<div class="logout">
				<form action="${contextPath}/logout" method="post">
					<input type="submit" class="nav-button" value="Logout" />
				</form>
			</div>
		</div>

		<div class="content">
			<h2>Update Student Information</h2>

			<!-- Display error message if available -->
			<c:if test="${not empty error}">
				<p class="error-message">${error}</p>
			</c:if>

			<!-- Display success message if available -->
			<c:if test="${not empty success}">
				<p class="success-message">${success}</p>
			</c:if>

			<form action="${contextPath}/modifyStudents" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="studentId" value="${student.id}">

				<div class="row">
					<div class="col">
						<label for="firstName">First Name:</label> <input type="text"
							id="firstName" name="firstName" value="${student.firstName}"
							required>
					</div>
					<div class="col">
						<label for="lastName">Last Name:</label> <input type="text"
							id="lastName" name="lastName" value="${student.lastName}"
							required>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="username">Username:</label> <input type="text"
							id="username" name="username" value="${student.userName}"
							required>
					</div>
					<div class="col">
						<label for="birthday">Date of Birth:</label> <input type="date"
							id="birthday" name="dob" value="${student.dob}" required>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="gender">Gender:</label> <select id="gender"
							name="gender" required>
							<option value="male"
								${student.gender == 'male' ? 'selected' : ''}>Male</option>
							<option value="female"
								${student.gender == 'female' ? 'selected' : ''}>Female</option>
						</select>
					</div>
					<div class="col">
						<label for="email">Email:</label> <input type="email" id="email"
							name="email" value="${student.email}" required>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="phoneNumber">Phone Number:</label> <input type="tel"
							id="phoneNumber" name="phoneNumber" value="${student.number}"
							required>
					</div>
					<div class="col">
						<label for="subject">Subject:</label> <select id="subject"
							name="subject" required>
							<option value="computing"
								${student.program.name.toLowerCase() == 'computing' ? 'selected' : ''}>Computing</option>
							<option value="multimedia"
								${student.program.name.toLowerCase() == 'multimedia' ? 'selected' : ''}>Multimedia</option>
							<option value="networking"
								${student.program.name.toLowerCase() == 'networking' ? 'selected' : ''}>Networking</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="image">Profile Picture:</label> <input type="file"
							id="image" name="image">
					</div>
				</div>
				<button type="submit">Update Student</button>
			</form>
		</div>
	</div>

</body>
</html>
