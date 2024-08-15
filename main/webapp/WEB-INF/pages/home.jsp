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
	
	<div class="main-body">
		This is our Home Page
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>