<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="web.app.eng.dto.User" %>

<html>

<%
User user = (User) session.getAttribute("user");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>userEditProfile</title>
</head>

<body>
	<h1>Edit Profile</h1>
	<h2><%=user.getUsername() %></h2>
	
	<form action="userControl" method="POST">
		<input type="hidden" name="action" value="update">
		
		<%=user.getFirstname() %> <%=user.getSurname() %><br/>
		<input type="text" name="firstname" placeholder="New Firstname"><!--
		--><input type="text" name="surname" placeholder="New Surname"><br/>
		
		<%=user.getEmail() %><br/>
		<input type="text" name="email" placeholder="New Email address" size="45"><br/>
		<input type="password" name="password" placeholder="New password" size="45"><br/><br/>
		
		Date of Birth<br/>
		<select name="birthdate">
			<%for (int i=31; i>=1; i--) { %>
				<%if (i == user.getBirthdate()) { %>
					<option value="<%=i %>" selected><%=i %></option>
				<%} else { %>
					<option value="<%=i %>"><%=i %></option>
				<%} %>
			<%} %>
		</select><!--
		--><select name="birthmonth">
			<%for (int i=12; i>=1; i--) { %>
				<%if (i == user.getBirthmonth()) { %>
					<option value="<%=i %>" selected><%=i %></option>
				<%} else { %>
					<option value="<%=i %>"><%=i %></option>
				<%} %>
			<%} %>
		</select><!--
		--><select name="birthyear">
			<%for (int i=2017; i>=1905; i--) { %>
				<%if (i == user.getBirthyear()) { %>
					<option value="<%=i %>" selected><%=i %></option>
				<%} else { %>
					<option value="<%=i %>"><%=i %></option>
				<%} %>
			<%} %>
		</select><br/><br/>
		
		Gender<br/>
		<%if (user.getGender().equals("male")) { %>
			<input type="radio" name="gender" value="male" checked>Male
		<%} else { %>
			<input type="radio" name="gender" value="male">Male
		<%} %>
		<%if (user.getGender().equals("female")) { %>
			<input type="radio" name="gender" value="female" checked>Female
		<%} else { %>
			<input type="radio" name="gender" value="female">Female
		<%} %><br/><br/>
		
		<input type="submit" value="Save">
	</form>
</body>

</html>