<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/validation.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="validation.jsp" />

	<div class="main-body">This is our Home Page</div>

	<jsp:include page="footer.jsp" />
</body>
</html>