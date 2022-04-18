<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Information</title>
</head>
<%
	String firstName = request.getParameter("first");
	String lastName = request.getParameter("last");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	String state = request.getParameter("state");
	
%>
<body>
<h1>User info</h1>
<table border="1">
	<tbody>
		<tr>
			<td>First name</td>
			<td><%= firstName %></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><%= lastName %></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><%= email %></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td><%= gender %></td>
		</tr>
		<tr>
			<td>State</td>
			<td><%= state %></td>
		</tr>
	</tbody>
</table>
</body>
</html>