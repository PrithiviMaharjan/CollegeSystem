<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${empty totalStudent }">
		<h1>0</h1>
	</c:if>
	<c:if test="${not empty totalStudent }">
		<h1>${totalStudent}</h1>
	</c:if>

	<div>
		<h1>Total Students</h1>
		<h3>
			<c:out value="${totalStudnet == null ? 0 : totalStudent}" />
		</h3>
	</div>
</body>
</html>