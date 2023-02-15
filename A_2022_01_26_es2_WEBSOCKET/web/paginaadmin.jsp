<!-- accesso alla sessione -->
<%@ page session="true"%>
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
		<span style="float:left">PAGINE DEL SITO </span>
		<nav style="float:right">
			<a href="login.jsp">LOGIN</a> |
			<a href="prenotazione.jsp">PRENOTA UN CAMPO</a> |
			<a href="listaprenotazioni.jsp">GIOCA UNA PARTITA</a> |
			<a href="paginaadmin.jsp">DASHBOARD ADMIN</a>
		</nav>
		</span>
		<br/><br/>
		<div id="main" class="clear">
					<%
						Attrazione u=(Attrazione)session.getAttribute("currentUser");
								if(u != null){
								if(!u.getUsername().equals("admin")){
					%>
						<h3>La pagina &egrave; riservata agli amministratori</h3>	
						<%
								}else{
										Richiesta prenotazioni=(Richiesta)application.getAttribute("listaPrenotazioni");
										if(prenotazioni==null){
							%>
			<h3>Nessuna prenotazione in sospeso</h3>	
			<%}else{ %>
			<h3>Lista prenotazioni in attesa di giocatori</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Campo</th>
						<th style="width: 31%">Tipologia</th>
						<th style="width: 31%">Giocatori iscritti</th>
						<th style="width: 31%">Data</th>
						<th style="width: 31%">Ora</th>
						<th style="width: 31%">Data prenotazione</th>
					</tr>
			<%
			
			for( Prenotazione anItem : prenotazioni.getItems() ){  
				if(anItem.isDefinitiva()==false){
			
					%>
					<tr>
							<td><%= anItem.getNumerocampo() %></td>
							<td><%= anItem.getTipologia() %> </td>
							<td><%= anItem.getGiocatori().size()%></td>
							<td><%= anItem.getDatag() %></td>
							<td><%= anItem.getOrario() %></td>
							<td><%= anItem.getDataoraprenotazione() %></td>
						</tr>
					<% } 
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
					}
					}%>
		</div>
	


	</body>
</html>
