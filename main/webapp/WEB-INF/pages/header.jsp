<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);

String contextPath = request.getContextPath();

String actionUrl;
String formMethod;
String buttonLabel;

if (currentUser != null) {
	actionUrl = contextPath + "/logout";
	formMethod = "post";
	buttonLabel = "Logout";
} else {
	actionUrl = contextPath + "/login";
	formMethod = "get";
	buttonLabel = "Login";
}
%>
<div id="header">
	<header class="header">
		<h1 class="logo">
			<a href=""><img
				src="${pageContext.request.contextPath}/resources/images/system/logo.png" /></a>
		</h1>
		<ul class="main-nav">
			<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
			<li><a href="${pageContext.request.contextPath}/about">About</a></li>
			<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
			<li>
				<form action="<%=actionUrl%>" method="<%=formMethod%>">
					<input type="submit" value="<%=buttonLabel%>" />
				</form>
			</li>
		</ul>
	</header>
</div>
