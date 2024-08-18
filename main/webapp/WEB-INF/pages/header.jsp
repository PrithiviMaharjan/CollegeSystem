<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
// need to add data in attribute to select it in JSP code using JSTL core tag
pageContext.setAttribute("currentUser", currentUser);
%>

<!-- Set contextPath variable -->
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="header">
	<header class="header">
		<h1 class="logo">
			<a href="${contextPath}"><img
				src="${contextPath}/resources/images/system/logo.png" alt="Logo" /></a>
		</h1>
		<ul class="main-nav">
			<li><a href="${contextPath}/home">Home</a></li>
			<li><a href="${contextPath}/about">About</a></li>
			<li><a href="${contextPath}/portfolio">Portfolio</a></li>
			<li><a href="${contextPath}/contact">Contact</a></li>
			<c:if test="${empty currentUser}">
				<li><a href="${contextPath}/register">Register</a></li>
			</c:if>
			<li><c:choose>
					<c:when test="${not empty currentUser}">
						<form action="${contextPath}/logout" method="post">
							<input type="submit" class="nav-button" value="Logout" />
						</form>
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/login">Login</a>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</header>
</div>
