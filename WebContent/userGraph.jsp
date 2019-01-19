<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.List" %>
<%@ page import="org.json.JSONObject" %>

<html>


<style>
body {
	margin:0;
}

.inline-form {
	display: inline-block;
}
</style>

<%
List<JSONObject> relations = (List<JSONObject>) request.getAttribute("relations");
List<JSONObject> entities = (List<JSONObject>) request.getAttribute("entities");
%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>visual</title>
</head>

<link rel="stylesheet" type="text/css" href="visjs/css/vis.css">
<script type="text/javascript" src="visjs/js/vis.js"></script>

<body>
	<form class="inline-form" action="userControl" method="POST">
		<input type="hidden" name="action" value="graphSearch">
		<select name="tagName">
			<option value="user">People</option>
			<option value="post">Post</option>
			<option value="friendsOf">Friends of Friend</option>
		</select>
		<input name="searchValue" type="text">
		<input type="submit" value="Search">
	</form>
	
	<div id="media" style="width:100%;height:500px"></div>
</body>

<script type="text/javascript">
var myDiv = document.getElementById("media");

var nodes = new vis.DataSet(<%=entities %>);

var edges = new vis.DataSet(<%=relations %>);

var data = {
		nodes: nodes,
		edges: edges
}

var options = {
		nodes : {
			size: 40,
			shadow: true
		},
		edges : {
			width: 5,
			length: 300,
			shadow: true
		}
};

var network = new vis.Network(myDiv, data, options);
</script>

</html>