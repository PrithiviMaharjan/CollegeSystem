<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashboard.css" />
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
			<div class="header">
				<div class="info-box">
					<h3>Total Students</h3>
					<p>${empty total ? 0 : total}</p>
				</div>
				<div class="info-box">
					<h3>Computing</h3>
					<p>${empty computing ? 0 : computing}</p>
				</div>
				<div class="info-box">
					<h3>Multimedia</h3>
					<p>${empty multimedia ? 0 : multimedia}</p>
				</div>
				<div class="info-box">
					<h3>Networking</h3>
					<p>${empty networking ? 0 : networking}</p>
				</div>
			</div>

			<div class="table-container">
				<!-- Display error message if available -->
				<c:if test="${not empty error}">
					<p class="error-message">${error}</p>
				</c:if>

				<!-- Display success message if available -->
				<c:if test="${not empty success}">
					<p class="success-message">${success}</p>
				</c:if>
				<h3>Student List</h3>
				<table>
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Module</th>
							<th>Email</th>
							<th>Number</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<!-- Using JSTL forEach loop to display student data -->
						<c:forEach var="student" items="${studentList}">
							<tr>
								<td>${student.id}</td>
								<td>${student.firstName} ${student.lastName}</td>
								<td>${student.program.name}</td>
								<td>${student.email}</td>
								<td>${student.number}</td>
								<td>
									<form action="${contextPath}/modifyStudents" method="post"
										style="display: inline;">
										<input type="hidden" name="studentId" value="${student.id}">
										<input type="hidden" name="action" value="updateForm">
										<button class="action-btn" type="submit">Edit</button>
									</form>
									<form action="${contextPath}/students" method="post"
										style="display: inline;">
										<input type="hidden" name="studentId" value="${student.id}">
										<input type="hidden" name="action" value="delete">
										<button class="action-btn" type="submit">Delete</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>