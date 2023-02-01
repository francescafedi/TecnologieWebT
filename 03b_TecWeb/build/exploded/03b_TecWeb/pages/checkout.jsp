<%@ page errorPage="../errors/failure.jsp"%>

<%!double totale(Counter cart){
	double tot=0;
	for( Item i :cart.getItems()){
		tot=tot+(i.getPrice()*cart.getOrder(i));
	}
return tot;	
}

void checkout(Counter cart, Catalogue cat){
	for( Item i : cart.getItems()){
		for (Item c : cat.getItems()){
			if(c.getDescription().equals(i.getDescription())){ // se è l'tem
				if(cart.getOrder(i)>c.getQuantity()){
					//errore 
				}else{
					c.setQuantity(c.getQuantity()-cart.getOrder(i)); //Decremento la quantità
				}
			}
		}
	}
	cart.empty();

}%>

<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Counter"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Checkout JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	
			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Catalogue" scope="application" />
			<jsp:useBean id="cart" class="it.unibo.tw.web.beans.Counter" scope="session" />
			<%
				if(request.getParameter("checkout")!=null && request.getParameter("checkout").equals("Concludi l'ordine")){
					//Creare l'item da passare alla funzione checkout
					checkout(cart,catalogue);
				}
			%>

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
	
		<div id="main" class="clear">
			<p>
				Concludi l'ordine
			</p>
			<p>Current cart:</p>
				<table class="formdata">
					<tr>
						<th style="width: 33%">Description</th>
						<th style="width: 33%">Price</th>
						<th style="width: 33%">Your order</th>
					</tr>
					<% 
					Item[] cartItems = cart.getItems().toArray(new Item[0]);
					for( Item aCartItem : cartItems ){  
					%> 
						<tr>
							<td><%= aCartItem.getDescription() %></td>
							<td><%= aCartItem.getPrice() %> &#8364;</td>
							<td><%= cart.getOrder(aCartItem) %></td>
						</tr>
						<tr>
					<td><form><input type="hidden" name="description" value="<%= aCartItem.getDescription() %>"/>
									<input type="hidden" name="quantity" value="<%= aCartItem.getQuantity() %>"/>
									<input type="hidden" name="price" value="<%= aCartItem.getPrice() %>"/>
									<input type="submit" name="checkout" value="Concludi l'ordine"/></form>
								</td>
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
				<p>Il totale: <%= totale(cart) %></p>
		</div>
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
