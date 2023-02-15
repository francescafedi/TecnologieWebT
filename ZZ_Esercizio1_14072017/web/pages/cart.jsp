<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Counter"%>
<%@ page import="it.unibo.tw.web.beans.Attrazione"%>
<%@ page import="it.unibo.tw.web.beans.Group"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import ="java.util.Map"%>
<%@ page import ="java.util.Set"%>


<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Catalogue JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">

			
			
			
			<%
													/* IL PROBLEMA + CHE NON SO IL MIO GRUPPO E IL MIO UTENTE - PER QUESTO DEVO SETTARE ATTRIBUTI DI SESSION O SERVLETCONTEXT*/
																								Map<String,Group> gruppiGlobal=(Map<String,Group>)application.getAttribute("gruppiGlobal");
																								Attrazione user=(Attrazione)session.getAttribute("userNow");
																								Group group=gruppiGlobal.get(user.getGroup()); //Il gruppo del mio utente
																								
																								for(Attrazione u : group.getUtenti()){
																									if(u.isLogin() && u.isFinalize()==false){
																										//Un utente del gruppo ha abbandonato il carrello
																										group.setCart(new Counter()); //resetto il carrello
																										group.getUtenti().clear(); //Tolgo le persone che erano entrate (resetto tutto)
																									}
																								}
																								
																								String description = request.getParameter("quantity");
																								
																								if ( description != null && ! description.equals("") ) {

																									if ( description.contains(" ") ) {
																										throw new Exception("Blanks are not allowed in the description field!"); 					
																									}
																								
																								if ( request.getParameter("add") != null && request.getParameter("add").equals("Inserisci") ) {
																									Counter c=new Counter();
																									c.setNumTickets(group.getCart().getNumTickets()+Integer.parseInt(description));
																									group.setCart(c);
																									user.setFinalize(true); //setto come finito l'acquisto
																									group.getUtenti().add(user);
																									}
																								
																								}
																								
																								gruppiGlobal.remove(user.getGroup());
																								gruppiGlobal.put(user.getGroup(), group);				
																								application.setAttribute("gruppiGlobal", gruppiGlobal );
																								session.setAttribute("userNow", user);
												%>
			<div id="left" style="float: left; width: 48%">

				<p>Inserisci la quantit� di biglietti desiderata:</p>
					<form action="cart.jsp">
						<input type="text" name="quantity" />	
						<input type="submit" name="add" value="Inserisci"/>
					</form>
							
			</div>
			<div id="right" style="float: right; width: 48%">
				<p>Attualmente hai selezionato il seguente numero di posti:</p>
				<input type="text" name="quantitySelected" value="<%= group.getCart().getNumTickets()%>"/>
			</div>
			
			
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
