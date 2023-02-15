<!-- accesso alla sessione -->
<%@ page session="true"%>

<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Attrazione"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>



<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		 <meta http-equiv="refresh" content="10; URL=paginaadmin.jsp">
		<title>Prenotazione campo</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	<span class="clear">
		<div id="main" class="clear">
					<%
						Map<Integer, Richiesta> richieste=(Map<Integer,Richiesta>)this.getServletContext().getAttribute("richieste");
								if(richieste==null){
					%>
			<h3>Nessuna richiesta effettuata</h3>	
			<%
					}else{
				%>
			<h3>Lista richeiste effettuate</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Id</th>
						<th style="width: 31%">Testo</th>
						<th style="width: 31%">Turno</th>
						<th></th>
					</tr>
			<%
				for( Richiesta anItem : richieste.values() ){
			%>
					<tr>
							<td><%=anItem.getId()%></td>
							<td><%=anItem.getTesto()%></td>
							<td><%=anItem.getTurno()%></td>
						</tr>
					<%
						}
					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>		
			<div id="result"></div>
			<%
				}
			%>
		</div>
	<div>
	<%
		Map<Integer, Attrazione> utenti=(Map<Integer,Attrazione>)this.getServletContext().getAttribute("utentiAttivi");
				if(utenti==null){
	%>
			<h3>Nessun utente attivo</h3>	
			<%
					}else{
				%>
			<h3>Lista utenti</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Username</th>
						<th></th>
					</tr>
			<%
				for( Attrazione anItem : utenti.values() ){
			%>
					<tr>
							<td><%= anItem.getUsername() %></td>
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
