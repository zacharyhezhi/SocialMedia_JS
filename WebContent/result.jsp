<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Admin" %>
<%@ page import="web.app.eng.dto.User" %>
<%@ page import="web.app.eng.service.UserService" %>

<html>

<%
Admin admin = (Admin) session.getAttribute("admin");
User user = (User) session.getAttribute("user");
List<User> results = (List<User>) session.getAttribute("searchResults");
%>

<style>
button.accordion {
	background-color: #eee;
	color: #444;
	cursor: pointer;
	padding: 18px;
	width: 100%;
	border: none;
	text-align: left;
	outline: none;
	font-size: 15px;
	transition: 0.4s;
}

button.accordion.active, button.accordion:hover {
	background-color: #ddd; 
}

div.panel {
	padding: 0 18px;
	display: none;
	background-color: white;
}
</style>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>result</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<h2>RESULT</h2>
		<ul class="list-group">
			<%if (results != null) { %>
				<%for (User result : results) {%>
					<%if (admin != null || !result.getUsername().equals(user.getUsername())) { %>
						<li class="list-group-item">
						<%if (admin != null) { %>
							<form action="adminControl" method="post">
								<input type="hidden" name="action" value="userActivityReport">
								<input type="hidden" name="username" value="<%=result.getUsername() %>">
								<input type="submit" value="<%=result.getUsername() %>" style="background:none; border-width:0px; color:blue; text-decoration:underline;">
							</form>
						<%} else { %>
							<form action="userControl" method="post">
								<input type="hidden" name="action" value="profile">
								<input type="hidden" name="username" value="<%=result.getUsername() %>">
								<input type="submit" value="<%=result.getUsername() %>" style="background:none; border-width:0px; color:blue; text-decoration:underline;">
							</form>
						<%} %>
						<%=result.getFirstname() %> <%=result.getSurname() %>
						</li>
					<%} %>
				<%} %>
			<%} %>
		</ul>
	</div>
</body>

</html>