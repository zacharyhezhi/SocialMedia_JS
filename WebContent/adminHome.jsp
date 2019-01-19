<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Post" %>

<html>

<%
String error = (String) request.getAttribute("error");
String isLoggedIn = (String) session.getAttribute("login");
String display = (String) session.getAttribute("display");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>adminHome</title>
</head>

<body>
	<%if (error != null) { %>
		<script>alert('${error}');</script>
	<%} %>

	<img src="https://www.unsw.edu.au/sites/default/files/UNSW_0.png" alt="UNSW Sydney">
	<%if (isLoggedIn == "true") { %>
		<jsp:include page="adminHeader.jsp"/>
		<%if (display == "result") { %>
			<jsp:include page="result.jsp"/>
		<%} else if (display == "userActivityReport") { %>
			<jsp:include page="adminUserActivityReport.jsp"/>
		<%} %>
	<%} else { %>
		<jsp:include page="adminHeaderLogin.jsp"/>
	<%} %>
</body>

</html>