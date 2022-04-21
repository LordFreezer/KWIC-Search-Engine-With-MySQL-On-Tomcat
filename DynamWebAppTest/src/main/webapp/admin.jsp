<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="PAK.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Test</title>

</head>
<body>
	<h1>KWIC+ Search System</h1>
	<form action="Update" method="post">
		<div>
			<h1>Admin Box</h1>
			<textarea id="admin" name="admin" rows="10" cols="70"></textarea>
		</div>
		<input type="button" value="Clear" name="clear" onclick="reload()" />
		<input type="submit" value="Submit" name="submit" />
		<!-- <input type="button" value="Submit" onclick="submit()"/> -->
	</form>
	<script>
		function reload() {
			window.location = window.location.href.split("?")[0];

		}
	</script>

</body>
</html>