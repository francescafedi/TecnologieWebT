<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		
	
		<div id="main" class="clear">
			<h3>Pagina di login</h3>
			<br/>
			<form name="login-form" action="loginServlet" method="post">
				Username<br>
				<input name="username" value="" /><br>
				Password<br>
				<input name="password" value="" type="password" /> <br>
				<button name="invio" type="submit" value="login">Login</button>
			</form>
			<%
		   if(session.getAttribute("resultLogin")!=null ){
			   if((int)session.getAttribute("resultLogin")==-1){
		   
		   %>
					<div id="result">
					Username o password errati.
					</div>
					<%} } %>
		</div>
	


	</body>
</html>
