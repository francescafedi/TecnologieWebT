<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="it.unibo.tw.web.beans.Risposta"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>



<html>
	<head>
		<meta name="Author" content="pisi79">
		 <meta http-equiv="refresh" content="5; URL=paginaadmin.jsp">
		<title>Prenotazione campo</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	<span class="clear">
		<div id="main" class="clear">
					<% 

					List<Risposta> prenotazioni=(List<Risposta>)application.getAttribute("listaRisposte");
					if(prenotazioni==null){
			%>
			<h3>Nessun tempo segnato</h3>	
			<%}else{ %>
			<h3>Lista tempi di risposta</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Parola</th>
						<th style="width: 31%">N° Servlet</th>
						<th style="width: 31%">Tempo impiegato</th>
					</tr>
			<%
			
			for( Risposta anItem : prenotazioni ){  
					%>
					<tr>
							<td><%= anItem.getTempoImpiegato() %></td>
							
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
