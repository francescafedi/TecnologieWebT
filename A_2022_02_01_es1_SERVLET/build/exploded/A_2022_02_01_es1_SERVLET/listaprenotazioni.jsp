<!-- accesso alla sessione -->
<%@ page session="true"%>
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Prenotazione"%>
<%@ page import="it.unibo.tw.web.beans.Attrazione"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>



<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Prenotazione campo</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
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
						Richiesta prenotazioni=(Richiesta)application.getAttribute("listaPrenotazioni");
										String numerocampo =request.getParameter("numerocampo");
										String datacampo =request.getParameter("data");
										String orariocampo =request.getParameter("orario");
										int data, orario;
										if ( numerocampo != null ) {
											int numero=Integer.parseInt(numerocampo);
											if ( datacampo != null ) {
												data=Integer.parseInt(datacampo);
											}else{
												throw new Exception("Blanks are not allowed in the description field!"); 
											}
												if ( orariocampo != null ) {
													 orario=Integer.parseInt(orariocampo);
												}else{
													throw new Exception("Blanks are not allowed in the description field!"); 
												}

											
											
											if ( request.getParameter("add") != null && request.getParameter("add").equals("ok") ) {
												for(Prenotazione p : prenotazioni.getItems()){
													if(numero==p.getNumerocampo()) {
														if(data==p.getDatag() && orario==p.getOrario()) {	
															Attrazione curent=(Attrazione)session.getAttribute("currentUser");
															p.getGiocatori().add(curent);	
															if(p.getTipologia().equals("doppio") && p.getGiocatori().size()==4){
																p.setDefinitiva(true);
															}
															if(p.getTipologia().equals("singolo") && p.getGiocatori().size()==2){
																p.setDefinitiva(true);
															}
														}
													}
												}
												
											}
											
											
										}
					%>
			<h3>Lista prenotazioni in attesa di giocatori</h3>	
			<br/>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Campo</th>
						<th style="width: 31%">Tipologia</th>
						<th style="width: 31%">Giocatori iscritti</th>
						<th style="width: 31%">Data</th>
						<th style="width: 31%">Ora</th>
						<th style="width: 31%">Prenota</th>
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
							<td>
								<a href="?add=ok&numerocampo=<%= anItem.getNumerocampo() %>&data=<%= anItem.getDatag() %>&orario=<%= anItem.getOrario() %>">
								Iscriviti</a>
							</td>
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
		</div>
	


	</body>
</html>
