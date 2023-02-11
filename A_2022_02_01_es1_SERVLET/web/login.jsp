<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	
		<span class="clear">
		<span style="float:left">PAGINE DEL SITO </span>
		<nav style="float:right">
			<a href="login.jsp">LOGIN</a> |
			<a href="prenotazione.jsp">PRENOTA UN CAMPO</a> |
			<a href="listaprenotazioni.jsp">GIOCA UNA PARTITA</a> |
			<a href="paginaadmin.jsp">DASHBOARD ADMIN</a>
		</nav>
		</span>
		<br/><br/>
		
	
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
			<div id="result"></div>
		</div>
	


	</body>
</html>
