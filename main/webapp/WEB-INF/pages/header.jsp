<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="header">
	<header class="header">
		<h1 class="logo">
			<a href=""><img
				src="${contextPath}/resources/images/system/logo.png" /></a>
		</h1>
		<ul class="main-nav">
			<li><a href="${contextPath}/home">Home</a></li>
			<li><a href="${contextPath}/about">About</a></li>
			<li><a href="${contextPath}/contact">Contact</a></li>
			<li><c:choose>
					<c:when test="${not empty sessionScope.username}">
						<form action="${contextPath}/logout" method="post">
							<input type="submit" value="Logout" />
						</form>
					</c:when>
					<c:otherwise>
						<form action="${contextPath}/login" method="get">
							<input type="submit" value="Login" />
						</form>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</header>
</div>
