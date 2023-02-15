<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="it.unibo.tw.web.beans.Risposta"%>
<%@ page import="it.unibo.tw.web.beans.Domanda"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>



<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		 <meta http-equiv="refresh" content="5; URL=paginaadmin.jsp">
		<title>Prenotazione campo</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	<span class="clear">
		<div id="main" class="clear">
					<% 
					List<Domanda> prenotazioni=(List<Domanda>)application.getAttribute("listaRisposte");
					if(prenotazioni==null){
			%>
			<h3>Nessun tempo segnato</h3>	
			<%}else{ %>
			<h3>Lista richieste</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Id Richiedente</th>
						<th style="width: 31%">N� richieste</th>
						<th style="width: 31%">Tempo da inizio</th>
					</tr>
			<%
			
			for( Domanda anItem : prenotazioni ){  
					%>
					<tr>
							<td><%= anItem.getSession().getId() %></td>
							<td><%= anItem.getCount() %></td>
							<td><%= anItem.getSession().getCreationTime() %></td>
							
						</tr>
					<%
					}%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>		
			<div id="result"></div>
			<%}
					%>
		</div>
	


	</body>
</html>
