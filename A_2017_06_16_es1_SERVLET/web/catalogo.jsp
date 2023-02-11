<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Catalogue"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>
<%@ page import="it.unibo.tw.web.beans.Group"%>
<%@ page import="it.unibo.tw.web.beans.Cart"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<!-- metodi richiamati nel seguito -->
<%!
void add(Cart carrello, Item item) {

	boolean alreadyInCatalogue = false;
	
	for ( Item itemInCatalogue : carrello.getItems() ) {
		if ( itemInCatalogue.getDescription().equals( item.getDescription() ) ) {
			itemInCatalogue.setQuantity( itemInCatalogue.getQuantity() + item.getQuantity() );
			alreadyInCatalogue = true;
			break;
		}
	}
	
	if ( ! alreadyInCatalogue ) {
		carrello.getItems().add( item );
	}
	
}

void remove(Catalogue catalogue, String description) {
	
	for ( int i = 0 ; i < catalogue.getItems().size() ; i++ ) {
		if ( catalogue.getItems().get(i).getDescription().equals(description) ) {
			catalogue.getItems().remove(i);
			break;
		}
	}

}

%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Catalogue JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	
	
		<div id="main" class="clear">

						<% 
					Group mygroup=(Group)session.getAttribute("mygroup");
					Cart carrello=mygroup.getCart();
					if(carrello==null){
						carrello=new Cart();
					}
				String description = request.getParameter("description");
	
				if ( description != null && ! description.equals("") ) {

					if ( description.contains(" ") ) {
						throw new Exception("Blanks are not allowed in the description field!"); 					
					}
					
					if ( request.getParameter("add") != null && request.getParameter("add").equals("ok") ) {
						Item item = new Item();
						item.setDescription( description );
						item.setPrice( Double.parseDouble( request.getParameter("price") ) );
						item.setQuantity( 1 );
						add(carrello,item);
						mygroup.setCart(carrello);
						//session.setAttribute("mygroup", mygroup);
					}
					else if ( request.getParameter("remove") != null && request.getParameter("remove").equals("ok") ) {
						//remove(carrello,description);
					}
					
				}
			%>
			

			
			<div id="left" style="float: left; width: 48%; border-right: 1px solid grey">
			<p>Catalogo:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Available quantity</th>
						<th style="width: 7%"></th>
					</tr>
					<% 
					Gson g=new Gson();
					String i=(String)application.getAttribute("catalogoJson");
					Item[] items = g.fromJson(i, Item[].class);
			
					for( Item anItem : items ){  
					%> 
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><%= anItem.getQuantity() %></td>
							<td>
								<a href="?add=ok&description=<%= anItem.getDescription() %>&price=<%= anItem.getPrice() %>">
								Aggiungi</a>
							</td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>			
				
			</div>
			
			<div id="right" style="float: right; width: 48%">
<p>Carrello:</p>
				<table class="formdata">
					<tr>
						<th style="width: 31%">Description</th>
						<th style="width: 31%">Price</th>
						<th style="width: 31%">Quantity</th>
						<th style="width: 7%">Totale</th>
					</tr>
					
						<%

					Item[] itemsCart = carrello.getItems().toArray(new Item[0]);
			
					for( Item anItem : itemsCart ){  
					%> 
						<tr>
							<td><%= anItem.getDescription() %></td>
							<td><%= anItem.getPrice() %> &#8364;</td>
							<td><%= anItem.getQuantity() %></td>
							<td><%= anItem.getQuantity()*anItem.getPrice() %>
							</td>
						</tr>
					<% }%>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>		

				
			</div>
			<a href="finalizza"><button type="submit" action="finalizza" name="finalizza" value="finalizza" style="width:100%; margin-top:30px">FINALIZZA</button>
		
			<div class="clear">
				<p>&nbsp;</p>
			</div>
			
		</div>
	

	</body>
</html>
