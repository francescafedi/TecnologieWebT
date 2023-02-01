<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <%@ page import="java.util.*" %>
    <%@ page import="beans.*" %>
<title>Amdmin Page</title>
</head>
<body>
<%  Map<String,GruppoUtenti> gruppi = (Map<String, GruppoUtenti>) application.getAttribute("gruppi");

	if(gruppi != null)
	{
		Collection<GruppoUtenti> gSet = gruppi.values();
		for(GruppoUtenti g : gSet)
		{
			%>
				<div>
				    <%=g.getNomeGruppo() %><br>
				    <%
				    	for(Utente u : g.getUtenti())
				    	{
				    		%>
				    		<p>
				    		<%=u.toString() %>
				    		</p>
				    		<%
				    	}
				    %>
				</div>
			<%
		}
	}
%>


</body>
</html>