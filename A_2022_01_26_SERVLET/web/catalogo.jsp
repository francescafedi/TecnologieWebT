
<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- accesso alla sessione -->
<%@ page session="true"%>

<!-- import di classi Java -->
<%@ page import="it.unibo.tw.web.beans.Richiesta"%>
<%@ page import="it.unibo.tw.web.beans.Item"%>

<%@ page import="it.unibo.tw.web.beans.User"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>


<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Catalogue JSP</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css"/>
		<script type="text/javascript" src="scripts/utils.js"></script>
	</head>

	<body>	
	
		<div id="main" class="clear">

						<%
							User current=(User)session.getAttribute("currentUser");
						if(current==null){
							%>Necessaria autenticazione<%
						}else{
						
						%>
			
		<p>Inserisci una stringa</p>
		<input name="parola" id="parola" value="" onchange="action( 'dis', myGetElementById('result') )" /><br>
			
			<%} %>
			<div id="result"></div>
		</div>
	

	</body>
</html>
