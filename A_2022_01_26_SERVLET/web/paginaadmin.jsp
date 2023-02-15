<!-- accesso alla sessione -->
<%@ page session="true"%>
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.*"%>
<%@ page import="it.unibo.tw.web.beans.User"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>



<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		 <meta http-equiv="refresh" content="5; URL=paginaadmin.jsp">
		<title>Pagina di amministrazione</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	
		<div id="main" class="clear">
					<%
						User u=(User)session.getAttribute("currentUser");
											if(u != null){
											if(!u.getUsername().equals("admin")){
					%>
						<h3>La pagina &egrave; riservata agli amministratori</h3>	
						<%
								}else{
										List<User> utenti=(List<User>)application.getAttribute("sessioniAtttive");
										if(utenti==null){
							%>
			<h3>Nessuna prenotazione in sospeso</h3>	
			<%}else{ %>
			<h3>Lista prenotazioni in attesa di giocatori</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Username</th>
						<th style="width: 31%">Num richieste</th>
						
					</tr>
			<%
			
			for( User anItem : utenti ){  
				
			
					%>
					<tr>
							<td><%= anItem.getUsername() %></td>
							<td><%= anItem.getNumRichiesta() %></td>
							
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
					}}%>
		</div>
	


	</body>
</html>
