<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Display error message if available -->
	<c:if test="${not empty error}">
		<p class="error-message">${error}</p>
	</c:if>

	<!-- Display success message if available -->
	<c:if test="${not empty success}">
		<p class="success-message">${success}</p>
	</c:if>

	<div class="main-body">This is our Home Page</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
