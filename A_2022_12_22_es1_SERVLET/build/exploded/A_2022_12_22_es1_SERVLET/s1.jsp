
<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Offerta"%>
<%@ page import="it.unibo.tw.web.beans.Regalo"%>
<%@ page import="it.unibo.tw.web.beans.User"%>
<%@ page import="it.unibo.tw.web.beans.Asta"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<!-- metodi richiamati nel seguito -->
<%%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Catalogue JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	
		<div id="main" class="clear">

						<%
						//Prima di fare l'invio attraverso javascript dovrei controllare le condizioni sui campi
						String nome = request.getParameter("nome");
						int offerta = Integer.parseInt(request.getParameter("offerta"));
						String continente = request.getParameter("continente");
							
								if ( nome != null && ! nome.equals("") ) {

									if ( nome.contains(" ") ) {
										throw new Exception("Blanks are not allowed in the description field!"); 					
									}
									
									if ( request.getParameter("add") != null && request.getParameter("add").equals("ok") ) {
										Offerta off=new Offerta();
										off.setContinente(continente);
										off.setDeanro(offerta);
										User current=(User)session.getAttribute("currentUser");
										off.setUtente(current);
										
										Gson g = new Gson();
										String oggettorisposta=g.toJson(off);
										String res=g.toJson(oggettorisposta);
										response.setHeader("Request Method", "post");
										response.getWriter().println(res);
										//mi fermo qui perchè non so mandare i dati in post
										//session.setAttribute("mygroup", mygroup);
									}
									else if ( request.getParameter("remove") != null && request.getParameter("remove").equals("ok") ) {
										//remove(carrello,description);
									}
									
								}
						%>
			

			
			<div id="left" style="float: left; width: 48%; border-right: 1px solid grey">
			<%
					Asta asta= (Asta)application.getAttribute("asta");
					if(asta.isAvviata()==false){ %>
						<p>Asta non ancora partita</p>
					<%}else{%>
					
			<p>Oggetto all'asta:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Nome oggetto</th>
						<th style="width: 31%">Descrizione oggetto</th>
						<th style="width: 31%">Prezzo oggetto</th>
						<th style="width: 31%">Continente oggetto</th>
						<th style="width: 31%">Prezzo offerta</th>
						<th style="width: 31%">Continente offerta</th>
						<th style="width: 31%">Azione</th>
						<th style="width: 7%"></th>
					</tr>
					<%
						
						for( Regalo anItem : asta.getRegali() ){
					%> 
						<tr>
							<td><%=anItem.getNome() %> </td>
							<td><%=anItem.getDescrizione() %> </td>
							<td><%=anItem.getPrezzo()%> &#8364;</td>
							<td><%=anItem.getContinente() %> </td>
							<td><input type="text" id="offerta"><input></td>
							<td><input type="text" id="continente"><input></td>
							<td><a href="?add=ok&nome=<%= anItem.getNome()%>&offerta=<script>document.getElmentById("offerta")</script>&continente=<script>document.getElmentById("continente")</script>">
							</td>
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
				<%
						} 
					%>
			</div>
			
		
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			
		</div>
	

	</body>
</html>
