<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
String contextPath = request.getContextPath();

// Determine action URL and button label based on user session
String actionUrl;
String formMethod;
String buttonLabel;

if (currentUser != null) {
	actionUrl = contextPath + "/logout";
	formMethod = "post"; // Call doPost in LogoutController
	buttonLabel = "Logout";
} else {
	actionUrl = contextPath + "/login";
	formMethod = "get"; // Call doGet in LoginController
	buttonLabel = "Login";
}
%>

<div id="header">
	<header class="header">
		<h1 class="logo">
			<a href=""><img
				src="<%=contextPath%>/resources/images/system/logo.png" /></a>
		</h1>
		<ul class="main-nav">
			<li><a href="#">Home</a></li>
			<li><a href="#">About</a></li>
			<li><a href="#">Portfolio</a></li>
			<li><a href="#">Contact</a></li>
			<li>
				<form action="<%=actionUrl%>" method="<%=formMethod%>">
					<input type="submit" value="<%=buttonLabel%>" />
				</form>
			</li>
		</ul>
	</header>
</div>