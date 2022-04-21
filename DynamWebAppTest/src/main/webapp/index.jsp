<%@ page 
	language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="PAK.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Test</title>

</head>
<body>
<h1> KWIC+ Search System</h1>
	<form action="index.jsp" method="get">
		<div>
			<h1>What would you like to search for?</h1>				
			<textarea id="in" name="in" rows="10" cols="50"></textarea>
		</div>
		<div>
			<h1>Search Results</h1>	
			<textarea id="out" rows="10" cols="70" readonly></textarea>
		</div>	
		<div>
			<h1>Admin Box</h1>	
			<textarea id="admin" name="admin" rows="10" cols="70"></textarea>
		</div>		
		<input type="reset" value="Clear" name="clear"/>
		<input type="submit" value="Submit" name="submit"/>
		<!-- <input type="button" value="Submit" onclick="submit()"/> -->
	</form>
	<%
	String myText = request.getParameter("in");
	String adminText = request.getParameter("admin");

	%>			
	<script>
	var bar = `<%=myText%>`;
		document.getElementById("in").innerHTML = bar;
	var tag = `<%=adminText%>`;
		document.getElementById("admin").innerHTML = tag;
	</script>
	<%
	
	if (myText == null) {
		// myText is null when the page is first requested, 
		// so do nothing
		%>			
		<script>
		var bar = `<%=""%>`;
			document.getElementById("in").innerHTML = bar;
		var tag = `<%=""%>`;
			document.getElementById("admin").innerHTML = tag;
		</script>
		<%
	} else { 
		if (myText.length() == 0) {
		// There was a querystring like ?myText=
		// but no text, so myText is not null, but 
		// a zero length string instead.
	%>
	<b>Something Went Wrong Somewhere</b>
	<% } else { 
						
		LineStorage local = new LineStorage();
		
		/*
Hello World https//www.hello.world
This is a test https//www.test.org
Shared Data Design https//www.sharedata.design
Another line https//www.one.more
Big Beefy boi with lots and lots of words https//www.gobigbeef.org
		*/
		
		String [] entireLine = adminText.split("\n");		
		for(int i = 0; i < entireLine.length; i++){
			int pos = entireLine[i].indexOf("http");
			
			String first = entireLine[i].substring(0, pos);		
			first = first.replaceAll("\\s+$", "").replaceAll("\n", "").replaceAll("\r", "");
			
			String second = entireLine[i].substring(pos);		
			second = second.replaceAll("\n", "").replaceAll("\r", "");
			
			//System.out.println("|"+first+"|");
			//System.out.println("|"+second+"|");
			
			local.addLine(local, first, second);
			
			
		}	
	
	myText = myText.replaceAll(" ", "");
	MastControl controller = new MastControl(local,myText);	
	
	
	
	
	String t2="";
	
	for(int i : controller.purged){
		controller.displayLine(local, i);
	}
	%>			
	<script>
	var foo = `<%=t2%>`;
		document.getElementById("out").innerHTML = foo;
	</script>
	<%
		}
	}
	%>

</body>
</html>