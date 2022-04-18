<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Test</title>
</head>
<body>
<h1> Form Demo</h1>
	<form name="myForm" action="results.jsp" method="post">
		<table>
			<tbody>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="first" value="" size="50"/></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="last" value="" size="50"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" value="" size="50"/></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td>
					<input type="radio" name="gender" value="Male"/> Male 
					<input type="radio" name="gender" value="Female"/> Female 
				</td>
			</tr>
			
			<tr>
				<td>Where were you born?:</td>
				<td>
					<select name="state">
						<option value=" ">Choose a state...</option>
						<option value="OK">Oklahoma</option>
						<option value="KS">Kansas</option>
						<option value="TX">Texas</option>
					</select>
				</td>
			</tr>
			</tbody>
		</table>
		<input type="reset" value="Clear" name="clear"/>
		<input type="submit" value="Submit" name="submit"/>
	</form>

</body>
</html>