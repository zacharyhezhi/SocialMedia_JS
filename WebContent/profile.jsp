<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Log" %>
<%@ page import="web.app.eng.dto.User" %>
<%@ page import="web.app.eng.service.UserService" %>

<html>

<%
User user = (User) session.getAttribute("user");
User otherUser = (User) session.getAttribute("otherUser");
UserService userService = new UserService();
Boolean isFriend = userService.isFriend(user.getUsername(), otherUser.getUsername());
Boolean isFriendRequestSent = userService.isFriendRequestSent(user.getUsername(), otherUser.getUsername());
%>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>profile</title>
	<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Armata">
	<link rel="stylesheet" href="assets/css/MUSA_timeline.css">
	<link rel="stylesheet" href="assets/css/MUSA_timeline1.css">
	<link rel="stylesheet" href="assets/css/Responsive-feedback-form.css">
	<link rel="stylesheet" href="assets/css/Responsive-feedback-form1.css">
	<link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
	<div>
		<div class="container">
			<h2>OTHER PROFILE</h2>
			<%if (!isFriend) { %>
				<%if (!isFriendRequestSent) { %>
					<form action="userControl" method="POST">
						<input type="hidden" name="action" value="addFriend">
						<input type="hidden" name="username" value="<%=otherUser.getUsername() %>">
						<input type="hidden" name="email" value="<%=otherUser.getEmail() %>">
						<input type="submit" value="Add Friend">
					</form>
				<%} else { %>
					<h3>Friend Request Sent</h3>
				<%} %>
			<%} else { %>
				<h3>Friends</h3>
			<%} %>
			<div class="row" style="padding-top:90px;">
				<div class="col-md-12" style="width:300px;"><img src="assets/img/empty-profile.png" style="width:240px;height:240px;"></div>
				<div class="col-md-12" style="width:620px;">
					<textarea style="width:600px;">Username : <%=otherUser.getUsername() %></textarea>
					<textarea style="width:600px;">Firstname : <%=otherUser.getFirstname() %></textarea>
					<textarea style="width:600px;">Surname : <%=otherUser.getSurname() %></textarea>
					<textarea style="width:600px;">Gender : <%=otherUser.getGender() %></textarea>
					<textarea style="width:600px;">Email : <%=otherUser.getEmail() %></textarea>
					<textarea style="width:600px;">DoB : <%=otherUser.getBirthdate() %>/<%=otherUser.getBirthmonth() %>/<%=otherUser.getBirthyear() %></textarea>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>