<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Home JSP</title>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/default.css" type="text/css"/>
		<script type="text/javascript" src="scripts/utils.js"></script>
	</head>

	<body>	

		
	
		<div id="main" class="clear">
			<h3>Pagina di richiesta</h3>
			<br/>
		
				Posizione x<br>
				<input name="x" id="x" value="" /><br>
				Posizione y<br>
				<input name="y" id="y" value="" /> <br>
				<button name="invio" onclick="caricaFeed( 'dis', myGetElementById('result') )" value="invio">Scopri attrazioni vicine</button>
		
			<div id="result"></div>
		</div>
	


	</body>
</html>
