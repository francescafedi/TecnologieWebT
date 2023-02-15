<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Counter"%>
<%@ page import="it.unibo.tw.web.beans.Regalo"%>

<!-- metodi richiamati nel seguito -->
<%!void add(Counter cart, Regalo item, int order) {

	boolean alreadyInCart = false;
	
	for ( Regalo itemInCart : cart.getItems() ) {
		if ( itemInCart.getDescription().equals( item.getDescription() ) ) {
			alreadyInCart = true;
			order += cart.getOrder(itemInCart);
			cart.put(item, order);
			break;
		}
	}
	
	if ( ! alreadyInCart ) {
		cart.put(item,order);
	}
			
}

double total(Counter cart) {
	double total = 0;
	for ( Regalo item : cart.getItems() ) {
		total += item.getPrice() * cart.getOrder(item);
	}
	return total;
}%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Cart JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">

			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Richiesta" scope="application" />
			<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Counter" scope="session" />
			
<%
				if ( request.getParameter("empty") != null && request.getParameter("empty").equals("ok") ) {
					cart.empty();
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
			%>			
			
			<div id="left" style="float: left; width: 48%; border-right: 1px solid grey">
			
				<p>Select an item from the catalogue:</p>
				<table class="formdata">
					<tr>
						<th style="width: 25%">Description</th>
						<th style="width: 25%">Price</th>
						<th style="width: 25%">Your order</th>
						<th style="width: 25%"></th>
					</tr>
					<%
						Regalo[] catalogueItems = catalogue.getItems().toArray(new Regalo[0]);
								for( Regalo aCatalogueItem : catalogueItems ){
					%> 
						<form>
							<tr>
								<td><%=aCatalogueItem.getDescription()%></td>
								<td><%=aCatalogueItem.getPrice()%> &#8364;</td>
								<td><input type="text" name="order" style="background-color: #c3c3d7;"/></td>
								<td>
									<input type="hidden" name="description" value="<%=aCatalogueItem.getDescription()%>"/>
									<input type="hidden" name="quantity" value="<%=aCatalogueItem.getQuantity()%>"/>
									<input type="hidden" name="price" value="<%=aCatalogueItem.getPrice()%>"/>
									<input type="submit" name="add" value="add to cart"/>
								</td>
							</tr>
						</form>
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
			</div>
			
			<div id="right" style="float: right; width: 48%">
				<p>Cart content:</p>
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
				<br/>
				<p>
				Total: <span style="font-size: x-large; color: red;"><%= total(cart) %> &#8364;</span>
				</p>
				
				<%
				if ( cart.getItems().size() > 0 ) {
				%>
					<br/>
					<a href="?empty=ok">Remove all items from the cart</a>
				<%
				}
				%>
			</div>
		
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
