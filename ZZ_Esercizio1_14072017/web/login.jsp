<html>
	<head>
		<meta name="Author" content="pisi79">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
	</head>

	<body>	

		<%@ include file="../fragments/header.jsp" %>
	
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
	
		<%@ include file="../fragments/footer.jsp" %>

	</body>
</html>
