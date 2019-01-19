<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Log" %>
<%@ page import="web.app.eng.dto.User" %>
<%@ page import="web.app.eng.service.LogService" %>

<html>

<style>
body {
	margin:0;
}

.top-navigation {
	background-color: #FFD833;
	padding: 15px;
	border: 1px solid black;
}

.inline-form {
	display: inline-block;
}

.box {
	background-color: #FFD833;
	color: #8aa4be;
	font-size: 16px;
	height: 50px;
	cursor: pointer;

}

.box:hover {
	background-color: #dddddd;
}

.dropdown {
	overflow: visible;
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: white;
	border: outset;
	border-color: cornflowerblue;
	width: 200px;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	z-index: 1;
}

.dropdown:hover .dropdown-content {
	display: block;
}
</style>

<%
User user = (User) session.getAttribute("user");
LogService logService = new LogService();
List<Log> notifications = logService.getNotifications(user.getUsername());
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>userHeader</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Armata">
	<link rel="stylesheet" href="assets/css/MUSA_timeline.css">
	<link rel="stylesheet" href="assets/css/MUSA_timeline1.css">
	<link rel="stylesheet" href="assets/css/Navigation-with-Search1.css">
	<link rel="stylesheet" href="assets/css/Responsive-feedback-form.css">
	<link rel="stylesheet" href="assets/css/Responsive-feedback-form1.css">
	<link rel="stylesheet" href="assets/css/styles.css">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="top-navigation">
		<form class="inline-form" action="userControl" method="POST">
			<input type="hidden" name="action" value="search">
			<select class="box" name="tagName">
				<option value="firstname">Firstname</option>
				<option value="surname">Surname</option>
				<option value="gender">Gender</option>
				<option value="dob">DoB</option>
			</select>
			<input name="searchValue" class="search-field" type="text">
			<button class="icon" type="submit" value="Search"><i class="glyphicon glyphicon-search"></i></button>
		</form>
		<form class="inline-form" action="userControl" method="POST" style="padding-left: 500px">
			<input type="hidden" name="action" value="userProfile">
			<input class="box" type="submit" value="Profile">
		</form>
		<form class="inline-form" action="userControl" method="POST">
			<input type="hidden" name="action" value="home">
			<input class="box" type="submit" value="Home">
		</form>
		<div class="dropdown">
			<button class="box">Notifications</button>
			<div class="dropdown-content">
				<%if (notifications != null) { %>
					<%int count = 0; %>
					<%for (Log notification : notifications) {%>
						<li>
						<%if (notification.getPredicate() == 2) { %>
							Received a friend request from <%=notification.getSubject() %><br/>
						<%} else if (notification.getPredicate() == 3) { %>
							<%=notification.getObject1() %> accepted your friend request<br/>
						<%} else { %>
							<%=notification.getSubject() %> liked your post <%=notification.getObject2() %><br/>
						<%} %>
						<%if (++count == 10) break; %>
						</li>
					<%} %>
				<%} %>
			</div>
		</div>
		<form class="inline-form" action="userControl" method="POST">
			<input type="hidden" name="action" value="graph">
			<input class="box" type="submit" value="Your Circle">
		</form>
		<form class="inline-form" action="userControl" method="POST">
			<input type="hidden" name="action" value="logout">
			<input class="box" type="submit" value="Log Out">
		</form>
	</div>
</body>

</html>
