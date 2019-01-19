<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<%
String username = (String) request.getAttribute("username");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>userAcceptFriend</title>
</head>

<body>
	<h1>Friend Request Accepted</h1>
	You have accepted <%=username %>'s friend request!<br/>
</body>

</html>