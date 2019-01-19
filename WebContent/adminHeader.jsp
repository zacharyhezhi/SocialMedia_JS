<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="web.app.eng.dto.Admin" %>

<html>

<style>
body {
	margin:0;
}

.top-navigation {
	background-color: red;
	padding: 15px;
	border: 1px solid black;
}

.inline-form {
	display: inline-block;
}

.box {
	background-color: red;
	color: White;
	font-size: 16px;
	height: 50px;
	cursor: pointer;
}

.box:hover {
	background-color: red;
}
</style>

<%
Admin admin = (Admin) session.getAttribute("admin");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>adminHeader</title>
</head>

<body>
	<div class="top-navigation">
		
		<form class="inline-form" action="adminControl" method="POST">
			<input type="hidden" name="action" value="search">
			<select class="box" name="tagName">
				<option value="firstname">Firstname</option>
				<option value="surname">Surname</option>
				<option value="gender">Gender</option>
				<option value="dob">DoB</option>
			</select>
			<input name="searchValue" class="box" type="text">
			<input class="box" type="submit" value="Search">
		</form>
		
		<form class="inline-form" action="adminControl" method="POST">
			<input type="hidden" name="action" value="logout">
			<input class="box" type="submit" value="Log Out">
		</form>
		
	</div>
</body>

</html>