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
<h1> Form Demo</h1>
	<form action="index.jsp">
		<table border="1">
			<tbody>							
				<tr>
					<td>
						<textarea id="in" name="in" rows="50" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<textarea id="out" rows="50" cols="50"></textarea>
					</td>
				</tr>
			</tbody>	
		</table>
		<input type="reset" value="Clear" name="clear"/>
		<input type="submit" value="Submit" name="submit"/>
		<!-- <input type="button" value="Submit" onclick="submit()"/> -->
	</form>
	<%
	String myText = request.getParameter("in");

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
	<b>myText is empty</b>
	<% } else { 
	String[] rowWords = myText.split("\n");
	for(int i = 0; i < rowWords.length; i++){
		rowWords[i] = rowWords[i].replace("\n", "").replace("\r", "");
		//System.out.print(rowWords[i]);
	}
	
	LineStorage local = new LineStorage();
	for (int i = 0; i < rowWords.length; i++) {
		String[] colWords = rowWords[i].split(" ");
		local.addLine();
		for (int j = 0; j < colWords.length; j++) {
			local.addWord();
			for (int h = 0; h < colWords[j].length(); h++) {
				local.addChar(colWords[j].charAt(h));
				// System.out.print(colWords[j].charAt(h));
			}
			 //System.out.print(' ');
		}
		// System.out.print('\n');
	}
	//System.out.println();
	
	MastControl controller = new MastControl(local);
	//System.out.println("ref Length: " + controller.reference.lineCount());
	int f = 0, g = 0, h = 0;
	String t2 = "";
	while (true) {
		if (controller.reference.getChar(f, g, h) == 0) {
			h = 0;
			g++;
			System.out.print(" ");
			 t2 =t2+ " ";
		}
		if (controller.reference.getChar(f, g, h) == 0) {
			g = 0;
			f++;
			System.out.print("\n");
			 t2 = t2 + "\n";
		}
		if (controller.reference.getChar(f, g, h) == 0) {
			%>			
			<script>
			var foo = `<%=t2%>`;
				document.getElementById("out").innerHTML = foo;
			</script>
			<%
			break;
		}
		System.out.print(controller.reference.getChar(f, g, h));
		
		t2 =t2+ controller.reference.getChar(f, g, h);
		h++;
	}
		}
	}
	%>

</body>
</html>