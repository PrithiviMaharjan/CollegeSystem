<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<%
	String errorMessage = (String) request.getAttribute("error");
	String successMessage = (String) request.getAttribute("success");

	if (errorMessage != null && !errorMessage.isEmpty()) {
		out.println("<p class=\"error-message\">" + errorMessage + "</p>");
	}

	if (successMessage != null && !successMessage.isEmpty()) {
	%>
	<p class="success-message"><%=successMessage%></p>
	<%
	}
	%>
	<div class="main-body">This is our Home Page</div>

	<jsp:include page="footer.jsp" />
</body>
</html>