<%@ page session="true"%>
<html>
   <head>
		<meta http-equiv="Refresh" content= "2; URL=home.html"/>
      <title>Start Web Application</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
   </head>
   <body>

      <!-- 
       ...so we offer the user something to read, meanwhile.
      
      This is the first page being shown, while the JSF Servlet starts up the environment,
      upon the first reqeust.
      This message avoid letting the user linger without knowing what's going on.
      -->
 
      <p class="asp">
      	
		<img class="wait" alt="wait" title="wait" src="images/wait.gif"/>
		L'applicazione sta per essere avviata...
      </p>

   </body>
</html>

