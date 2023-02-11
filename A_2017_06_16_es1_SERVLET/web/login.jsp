<html>
	<head>
		<meta name="Author" content="pisi79">
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
		<%	Integer result = (Integer)session.getAttribute("success");
      	if(result !=null)
      	{
      		switch(result)
      		{
      			case 1:
      			{
      				%>
      					<p>Ordine finalizzato con <strong>successo</strong> da parte sua, si attendono gli altri elementi del gruppo</p>
      				<% 
      			}break;
      			
      			case 2:
      			{
      				%>
  					<p>Tutti gli elementi del gruppo hanno finalizzato l'ordine: Ordine finalizzato con <strong>successo</strong> </p>
  					<% 
      			}break;
      			case 3:
      			{
      				%>
  					<p>Admin: finalizzato ordine gruppo con <strong>successo</strong> </p>
  					<% 
      			}break;
      			
      			case 4:
      			{
      				%>
  					<p>Sessione già attiva su un altro dispositivo</strong> </p>
  					<% 
      			}break;
      			
      			case 5:
      			{
      				%>
  					<p>Utente: il suo ordine è stato gestito dell'amministratore </p>
  					<% 
      			}break;
      		}
      		session.invalidate();
      	}
      	
      	%>
			<div id="result"></div>
		</div>
	


	</body>
</html>
