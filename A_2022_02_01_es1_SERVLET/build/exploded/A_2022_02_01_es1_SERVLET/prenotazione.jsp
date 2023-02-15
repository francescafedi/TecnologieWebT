<html>
	<head>
		<meta name="Author" content="Francesca Fedi">
		<title>Prenotazione campo</title>
		<script type="text/javascript" src="scripts/utils.js"></script>
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
			<h3>Pagina di prenotazione di un campo</h3>
			<br/>
				Numero di campo<br>
				<select required id="numerocampo" name="numerocampo">
				    <option value="1">1</option>
				    <option value="2">2</option>
				    <option value="3">3</option>
				    <option value="4">4</option>
				    <option value="5">5</option>
				    <option value="6">6</option>
				    </select>
				<br>Tipologia<br>
				<select required id="tipologia" name="tipologia">
				    <option value="singolo">singolo</option>
				    <option value="doppio">doppio</option>
				    </select>
				    <br>Data<br>
				<input required id="data" name="data" value="" /><br>
				Orario<br>
				<input required id="orario" name="orario" value="" /><br>
				<button name="invio" type="submit" value="login" onclick="valida('prenotazione', myGetElementById('result'))">Prenota</button>
				
			<div id="result"></div>
		</div>
	


	</body>
</html>
