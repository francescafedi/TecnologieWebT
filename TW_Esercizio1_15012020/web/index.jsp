<%@ page session="true"%>
<html>
   <head>
	<!-- 	<meta http-equiv="Refresh" content= "2; URL=paginaPrincipale"/> -->
      <title>Welcome Page</title>
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
      	Please choose your case to begin... &nbsp;
		<img alt="wait" title="wait" src="images/wait.gif"/>
      </p>


	  <div>
	  	<p><h5>Registration Form</h5></p>
	  	<form action="Registration" method="post">
	  		<input type="text" size="20" name="userName" hint="insert the username"><br>
	  		<input type="text" size="20" name="password" hint="insert the your pwd"><br>
	  		<input type="text" size="7" name="gruppo" hint="insert the group"><br>
	  		<input type="submit" value="Register">
	  	</form>
	  </div>
	  <div>
	  	<p><h5>Login Form</h5></p>
	  	<form action="Login" method="post">
	  		<input type="text" size="20" name="userName" hint="insert the username"><br>
	  		<input type="text" size="20" name="password" hint="insert the your pwd"><br>
	  		<input type="text" size="7" name="gruppo" hint="insert the group"><br>
	  		<input type="submit" value="Login">
	  	</form>
	  </div>
	  
	  <div>
	  	<%String err = (String)session.getAttribute("err");
	  	  if(err!=null)
	  	  {
	  		  int codiceErr = Integer.parseInt(err);
	  		  switch(codiceErr)
	  		  {
	  		  	case 1:
	  		  	{
	  			  %>
	  			  	<p><b> Errore: il gruppo selezionato non esiste</b></p>
	  			  <% 
	  		 	}break;
	  		 	
	  		  case 2:
	  		  	{
	  			  %>
	  			  	<p><b> Errore: L'utente non esiste per il gruppo selezionato</b></p>
	  			  <% 
	  		 	}break;
	  		 	
	  		case 3:
  		  	{
  			  %>
  			  	<p><b> Errore: Password utente scaduta, cambiarla presso la pagina registration</b></p>
  			  <% 
  		 	}break;
  		 	
	  		case 4:
  		  	{
  			  %>
  			  	<p><b> Errore: Nel Gruppo selezionato ci sono almeno 3 password scadute, cambiare la propria presso la pagina registration</b></p>
  			  <% 
  		 	}break;
  		 	
	  		case 5:
  		  	{
  			  %>
  			  	<p><b> Errore: Password sbagliata</b></p>
  			  <% 
  		 	}break;
  		 	
	  		case 6:
  		  	{
  			  %>
  			  	<p><b> Errore: Password sbagliata per piu di tre volte, utente bloccato</b></p>
  			  <% 
  		 	}break;
	  		  }
	  	  }
	  	%>
	  </div>
   </body>
</html>

