<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="java.util.List" %>
<%@ page import="web.app.eng.dto.Log" %>
<%@ page import="web.app.eng.dto.User" %>
<%@ page import="web.app.eng.service.LogService" %>

<html>

<%
User user = (User) session.getAttribute("user");
LogService logService = new LogService();
List<Log> activities = logService.getActivities(user.getUsername());
%>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>adminUserActivityReport</title>
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
			<h2>ACTIVITY</h2>
			<%if (!user.isBanned()) { %>
				<form action="adminControl" method="POST">
					<input type="hidden" name="action" value="ban">
					<input type="submit" value="Ban User">
				</form>
			<%} else { %>
				<form action="adminControl" method="POST">
					<input type="hidden" name="action" value="unban">
					<input type="submit" value="Unban User">
				</form>
			<%} %>
			<div class="row" style="padding-top:90px;">
				<div class="col-md-12" style="width:300px;"><img src="assets/img/empty-profile.png" style="width:240px;height:240px;"></div>
				<div class="col-md-12" style="width:620px;">
					<textarea style="width:600px;">Username : <%=user.getUsername() %></textarea>
					<textarea style="width:600px;">Firstname : <%=user.getFirstname() %></textarea>
					<textarea style="width:600px;">Surname : <%=user.getSurname() %></textarea>
					<textarea style="width:600px;">Gender : <%=user.getGender() %></textarea>
					<textarea style="width:600px;">Email : <%=user.getEmail() %></textarea>
					<textarea style="width:600px;">DoB : <%=user.getBirthdate() %>/<%=user.getBirthmonth() %>/<%=user.getBirthyear() %></textarea>
					
					<%if (activities != null) { %>
						<%for (Log activity : activities) {%>
							<%if (activity.getPredicate() == 1) { %>
								<%=activity.getDatetime() %> | <%=activity.getSubject() %> joined UNSWBook<br/>
							<%} else if (activity.getPredicate() == 2) { %>
								<%=activity.getDatetime() %> | <%=activity.getSubject() %> sent a friend request to <%=activity.getObject1() %><br/>
							<%} else if (activity.getPredicate() == 3) { %>
								<%=activity.getDatetime() %> | <%=activity.getObject1() %> accepted <%=activity.getSubject() %> friend request<br/>
							<%} else if (activity.getPredicate() == 4) { %>
								<%=activity.getDatetime() %> | <%=activity.getSubject() %> posted post <%=activity.getObject2() %><br/>
							<%} else if (activity.getPredicate() == 5) { %>
								<%=activity.getDatetime() %> | <%=activity.getSubject() %> liked post <%=activity.getObject2() %><br/>
							<%} else { %>
								<%=activity.getDatetime() %> | <%=activity.getSubject() %>'s post <%=activity.getObject2() %> contains reference to bullying<br/>
							<%} %>
						<%} %>
					<%} %>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>