<%@ page session="true"%>
<html>
   <head>
      <title>TW 16.6.2017 es1</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <p>
      	..Benvenuto, loggati... &nbsp;
      </p>
      <div>
      	<form action="LogIn" method="post">
      		<p>User:</p>
      		<input type="text" name="userName" size="30"/><br>
      		<p>Password:</p>
      		<input type="password" name="pwd" size="30"/><br><br>
      		<input type="submit" value="Log In"/>
      	</form>
      	<%
      	
      	Integer result = (Integer)session.getAttribute("success");
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
  					<p>Admin: Resettato ordine gruppo con <strong>successo</strong> </p>
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
      </div>

   </body>
</html>