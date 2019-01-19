<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>registration</title>
</head>

<body>
	<h1>Create a new account</h1>
	It's free and always will be.<br/><br/>
	
	<form action="userControl" method="POST">
	
		<input type="hidden" name="action" value="registration">
		
		<input type="text" name="firstname" placeholder="First name" required><!--
		--><input type="text" name="surname" placeholder="Surname" required><br/>
		
		<input type="text" name="username" placeholder="Username" size="45" required><br/>
		
		<input type="text" name="email" placeholder="Email address" size="45" required><br/>
		
		<input type="password" name="password" placeholder="New password" size="45" required><br/><br/>
		
		Birthday<br/>
		<select name="birthdate">
			<%for (int i=31; i>=1; i--) { %>
				<option value="<%=i %>"><%=i %></option>
			<%} %>
		</select><!--
		--><select name="birthmonth">
			<%for (int i=12; i>=1; i--) { %>
				<option value="<%=i %>"><%=i %></option>
			<%} %>
		</select><!--
		--><select name="birthyear">
			<%for (int i=2017; i>=1905; i--) { %>
				<option value="<%=i %>"><%=i %></option>
			<%} %>
		</select><br/><br/>
		
		<input type="radio" name="gender" value="male" required>Male
		<input type="radio" name="gender" value="female" required>Female<br/><br/>
		
		<input type="submit" value="Create Account">
	</form>
</body>

</html>