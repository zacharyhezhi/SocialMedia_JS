<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Post" %>
<%@ page import="web.app.eng.dto.User" %>

<html>

<%
String error = (String) request.getAttribute("error");
User user = (User) session.getAttribute("user");
String display = (String) session.getAttribute("display");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>userHome</title>
</head>

<body>
	<%if (error != null) { %>
		<script>alert('${error}');</script>
	<%} %>
	
	<img src="https://www.unsw.edu.au/sites/default/files/UNSW_0.png" alt="UNSW Sydney">
	
	<%if (user == null) { %>
		<jsp:include page="userHeaderLogin.jsp"/>
	<%} else { %>
		<jsp:include page="userHeader.jsp"/>
	<%} %>
	
	<%if (display == "confirmEmail") { %>
		<jsp:include page="userConfirmEmail.jsp"/>
	<%} else if (display == "registrationComplete") { %>
		<jsp:include page="userRegistrationComplete.jsp"/>
	<%} else if (display == "acceptFriend") { %>
		<jsp:include page="userAcceptFriend.jsp"/>
	<%} else if (user == null) { %>
		<jsp:include page="userRegistration.jsp"/>
	<%} else if (display == "userProfile") { %>
		<jsp:include page="userProfile.jsp"/>
	<%} else if (display == "editProfile") { %>
		<jsp:include page="userEditProfile.jsp"/>
	<%} else if (display == "result") { %>
		<jsp:include page="result.jsp"/>
	<%} else if (display == "profile") { %>
		<jsp:include page="profile.jsp"/>
	<%} else if (display == "graph") { %>
		<jsp:include page="userGraph.jsp"/>
	<%} else { %>
		<jsp:include page="userWall.jsp"/>
	<%} %>
	
</body>

</html>