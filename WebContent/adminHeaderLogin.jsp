<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<style>
body {
	margin:0;
}

.top-navigation {
	overflow: hidden;
	background-color: red;
	padding: 15px;
	border: 1px solid black;
}

.label {
	display: inline-block;
	width: 160px;
	height: 20px;
}

.box {
	background-color: red;
	color: White;
	font-size: 14px;
	border: 1px solid black;
	cursor: pointer;
}

.box:hover {
	background-color: red;
}
</style>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>adminHeaderLogin</title>
</head>

<body>
	<div class="top-navigation">
		<div class="label">Username</div><!--
		--><div class="label">Password</div>
		
		<form action="adminControl" method="POST">
			<input type="hidden" name="action" value="login">
			<input type="text" name="username" placeholder="Enter Username" required><!--
			--><input type="password" name="password" placeholder="Enter Password" required><!--
			--><input class="box" type="submit" value="Log In">
		</form>
	</div>
</body>

</html>