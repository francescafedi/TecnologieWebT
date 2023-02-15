<%@ page errorPage="../errors/failure.jsp"%>

<%!double totale(Counter cart){
	double tot=0;
	for( Regalo i :cart.getItems()){
		tot=tot+(i.getPrice()*cart.getOrder(i));
	}
return tot;	
}

void checkout(Counter cart, Richiesta cat){
	for( Regalo i : cart.getItems()){
		for (Regalo c : cat.getItems()){
			if(c.getDescription().equals(i.getDescription())){ // se � l'tem
				if(cart.getOrder(i)>c.getQuantity()){
					//errore 
				}else{
					c.setQuantity(c.getQuantity()-cart.getOrder(i)); //Decremento la quantit�
				}
			}
		}
	}
	cart.empty();

}%>

<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Counter"%>
<%@ page import="it.unibo.tw.web.beans.Regalo"%>
<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Checkout JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	
			<jsp:useBean id="catalogue" class="it.unibo.tw.web.beans.Richiesta" scope="application" />
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
						Regalo[] cartItems = cart.getItems().toArray(new Regalo[0]);
								for( Regalo aCartItem : cartItems ){
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
