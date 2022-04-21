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
		<!-- <div>
			<h1>Admin Box</h1>	
			<textarea id="admin" name="admin" rows="10" cols="70"></textarea>
		</div>	 -->	
		<input type="button" value="Clear" name="clear" onclick="reload()"/>
		<input type="submit" value="Submit" name="submit"/>
		<p>Always press "Clear" before performing a new search!</p>
		<!-- <input type="button" value="Submit" onclick="submit()"/> -->
	</form>
	<script>
	function reload(){
		window.location = window.location.href.split("?")[0];
		
	}
	</script>
	<%
	String myText = request.getParameter("in");
	//String adminText = request.getParameter("admin");

	%>			
	<script>
	var bar = `<%=myText%>`;
		document.getElementById("in").innerHTML = bar;

	</script>
	<%
	
	if (myText == null) {
		// myText is null when the page is first requested, 
		// so do nothing
		%>			
		<script>
		var bar = `<%=""%>`;
			document.getElementById("in").innerHTML = bar;
		
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
TEST DATA		
		
Hello World https//www.hello.world
This is a test https//www.test.org
Shared Data Design https//www.sharedata.design
Another line https//www.one.more
Big Beefy boi with lots and lots of words https//www.gobigbeef.org
		*/
		
		/*
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
			
			
		}	*/
		UpdateDao uDao = new UpdateDao();
		uDao.get();
		for(int i  = 0; i < uDao.lines.size(); i++){
			local.addLine(local, 
					uDao.lines.get(i).getDesc(),
					uDao.lines.get(i).getUrl() );
		}
				
	
	
	MastControl controller = new MastControl(local,myText);	
	
	//System.out.println("LOCAL LINE COUNT: "+local.lineCount());
	
	
	
	
	
	
	
	String t2="";
	
	for(int i : controller.purged){
		t2+=controller.displayLine(local, i)+"\n";
	}
	if(controller.purged.size() == 0){
		t2 = "No results found";
	}
	local.clear();
	controller.purged.clear();
	uDao.lines.clear();
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