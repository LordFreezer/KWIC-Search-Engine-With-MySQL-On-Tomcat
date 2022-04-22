<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="PAK.*" %>
<!DOCTYPE html>
<html>
<!-- Author: Chad Marshall -->
<head>
<meta charset="ISO-8859-1">
<title>Form Test</title>

</head>
<body>
<h1> KWIC+ Search System</h1>
<a href="./admin.jsp"><input type="button" value="Admin Page"></input></a>
	<form action="index.jsp" method="get">
		<div>
			<h1>What would you like to search for?</h1>				
			<textarea id="in" name="in" rows="10" cols="50"></textarea>
		</div>
		<div>
			<h1>Search Results</h1>	
			<textarea id="out" rows="10" cols="70" readonly></textarea>
		</div>		
		<input type="button" value="Clear" name="clear" onclick="reload()"/>
		<input type="submit" value="Submit" name="submit"/>
		<p>Always press "Clear" before performing a new search!</p>
		
	</form>
	<script>
	function reload(){
		// Reloads page and clears parameters
		window.location = window.location.href.split("?")[0];
		
	}
	</script>
	<%
	// Retrieves parameters in Java from same page on submit action
	String keyword = request.getParameter("in");
	

	%>			
	<script>
	// Interpolates the Java keyword string into something that JavaScript can understand
	// and populates the keyword box with keyword
	var bar = `<%=keyword%>`;
		document.getElementById("in").innerHTML = bar;
	</script>
	<%	
	if (keyword == null) {		
		// Do nothing
		%>			
		<script>
		// Clears keyword box
		var bar = `<%=""%>`;
			document.getElementById("in").innerHTML = bar;		
		</script>
		<%
	} else { 
		if (keyword.length() == 0) {
	%>
	<b>Something Went Wrong Somewhere</b>
	<%
	} else { 
			// Creates storage for descriptions
			LineStorage local = new LineStorage();
			// Establishes a connection to db
			UpdateDao uDao = new UpdateDao();
			// Retrieves the description and url pairs
			uDao.get();
			for(int i  = 0; i < uDao.lines.size(); i++){
		local.addLine(local, 
				uDao.lines.get(i).getDesc(),
				uDao.lines.get(i).getUrl() );
			}
			
		// shifts and alphabetizes the descriptions
		MastControl controller = new MastControl(local,keyword);	
		
		String t2="";
		
		// At this point, the indices for what results are retrieved.
		// Text for output box is built.
		for(int i : controller.indices){
			t2+=controller.displayLine(local, i)+"\n";
		}
		
		// Exception handling for invalid keyword
		if(controller.indices.size() == 0){
			t2 = "No results found";
		}
		// Purges everything
		local.clear();
		controller.indices.clear();
		uDao.lines.clear();
	%>			
	<script>
	// Output textbox is populated.
	var foo = `<%=t2%>`;
		document.getElementById("out").innerHTML = foo;
	</script>
	<%	
		}
	}
	%>

</body>
</html>