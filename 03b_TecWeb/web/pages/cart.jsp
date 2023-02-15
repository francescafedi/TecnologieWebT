<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Counter"%>
<%@ page import="it.unibo.tw.web.beans.Regalo"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import ="java.util.Map"%>
<%@ page import ="java.util.Set"%>

<!-- metodi richiamati nel seguito -->
<%!void add(Counter cart, Regalo item, int order) { //Perch� gli passo Cart? Non � un attributo di sessione?
	boolean alreadyInCart = false;
		for ( Regalo itemInCart : cart.getItems() ) {
			if ( itemInCart.getDescription().equals( item.getDescription() ) ) {
				order=cart.getOrder(itemInCart) + order; //setto la quantit� dell'item al suo valore di quanti + quanto ne voglio ordinare 
				//� gi� presente nel carrello
				cart.put(item,order);
				alreadyInCart = true;
				break;
			}
		}
		if ( ! alreadyInCart ) {
			cart.put(item, order);
		}
}

double totale(Counter cart){
	double tot=0;
	for( Regalo i :cart.getItems()){
		tot=tot+(i.getPrice()*cart.getOrder(i));
	}
return tot;	
}%>

<!-- codice html restituito al client -->
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

			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Richiesta" scope="application" />
			<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Counter" scope="session" />
			
			
			<%
										String description = request.getParameter("description");
										if ( description != null && ! description.equals("") ) {

											if ( description.contains(" ") ) {
												throw new Exception("Blanks are not allowed in the description field!"); 					
											}
											
											if ( request.getParameter("add") != null && request.getParameter("add").equals("add to cart") ) {
												Regalo item = new Regalo();
												item.setDescription( request.getParameter("description") );
												item.setPrice( Double.parseDouble( request.getParameter("price") ) );
												item.setQuantity( Integer.parseInt(request.getParameter("quantity") ) );
												int order = Integer.parseInt( request.getParameter("order") );
												if ( order > item.getQuantity() ) 
													throw new Exception("There are not enough items to complete the order!");
												add(cart,item,order);
											}

											/*	if ( request.getParameter("add") != null ) {
											Item i=new Item();
											
											i.setDescription(description);
											int order=0;
											Item[] items = catalogue.getItems().toArray(new Item[0]);
											for( Item itemTemp : items ){ 
											if(description.equals(itemTemp.getDescription())){
												i.setPrice(itemTemp.getPrice());
												if(request.getParameter("order")!=null){ 
												 	order=Integer.parseInt(request.getParameter("order"));
													if(order>itemTemp.getQuantity()){
														throw new Exception("Erroreeeeee");
													}
												}
											}
											}
											if(order>0){
												add(cart,i, order);	
											}
										}*/
										}
									%>
			<div id="left" style="float: left; width: 48%">

				<p>Current catalogue:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Quantity order</th>
						<th style="width: 7%"></th>
					</tr>
					<%
						Regalo[] items = catalogue.getItems().toArray(new Regalo[0]);
								for( Regalo anItem : items ){
					%> 
					<form>
						<tr>
							<td><%=anItem.getDescription()%></td>
							<td><%=anItem.getPrice()%> &#8364;</td>
							<td><input type="text" name="order"/></td>
							<td>
							<input type="hidden" name="description" value="<%=anItem.getDescription()%>"/>
									<input type="hidden" name="quantity" value="<%=anItem.getQuantity()%>"/>
									<input type="hidden" name="price" value="<%=anItem.getPrice()%>"/>
									<input type="submit" name="add" value="add to cart"/>
							</td>
						</tr>
						</form>
					<%
						}
					%>
				</table>			
			</div>
			
			<div id="right" style="float: right; width: 48%; border-right: 1px solid grey">
			
			
				<p>Current cart:</p>
				<table class="formdata">
					<tr>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Your order</th>
					</tr>
					<%
						Regalo[] cartItems = cart.getItems().toArray(new Regalo[0]);
								for( Regalo aCartItem : cartItems ){
					%> 
						<tr>
							<td><%= aCartItem.getDescription() %></td>
							<td><%= aCartItem.getPrice() %> &#8364;</td>
							<td><%= cart.getOrder(aCartItem) %></td>
						</tr>
					<% 
					} 
					%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
				
			</div>
			
					
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			<p>Il totale: <%= totale(cart) %></p>
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
