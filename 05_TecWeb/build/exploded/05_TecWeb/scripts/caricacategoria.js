/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */


function parsificaXmlCategoria( xml ) {
   
	// variabili di funzione
	var	items = xml.getElementsByTagName("item");
	var risultato=items[0].getElementsByTagName("title").item(0).firstChild.nodeValue;
	//var	itemNodestitle = items.getElementsByTagName("title")[0];
     return risultato;

}// parsificaXmlCategoriaCategoriaCategoriaCategoriaCategoria()

/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come html interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaCategoriaAJAX(theUri, theElement, theXhr) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() {
		// nota.. anche se la funzione e' assegnata a un campo di theXhr, 
		// dal momento che la funzione e' invocata dall'interprete in modo asincrono
		// dal suo interno devo necessariamente riferire  
		//	theXhr.status
		// chiamando l'oggetto cui appartiene status con il nome 
		// con cui e' noto nella chiusura della funzione
		// e non posso invece utilizzare la notazione
		//	this.status
		// come invece potrei fare se invocassi la funzione in modo sincrono

		// ---------------------------------------------------------------------------

		// designiamo la formattazione della zona dell'output
		theElement.class = "content";
	
		// verifica dello stato
		if ( theXhr.readyState === 2 ) {
		    	theElement.innerHTML = "Richiesta inviata...";
		} // if 2

		else if ( theXhr.readyState === 3 ) {
	    		theElement.innerHTML = "Ricezione della risposta...";
		} // else if 3

		else if ( theXhr.readyState === 4 ) {
			
			// verifica della risposta da parte del server
			if ( theXhr.status === 200 ) {
				theElement.innerHTML="Entrato";
				// operazione avvenuta con successo
				theElement.value= parsificaXmlCategoria(theXhr.responseXML);
				//theElement.innerHTML="Parse finito";
			} // if 200

			else {
				// errore di caricamento
				theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
				theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
			} // else (if !200)

		}// else if 4
		// ---------------------------------------------------------------------------
	} // callback function

	// impostazione richiesta asincrona in GET del file specificato
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised, for instance, when trying to access cross-domain URIs 
		alert(e);
	}

	// sostituzione dell'header "connection" (default = "keep alive")
	//theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

} // caricaCategoriaAJAX()



/*
 * Scarica un contenuto testuale dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function caricaCategoria(uri,e) {

	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();

	// try to place a breakpoint here and change xhr value to false!
	if ( xhr ) 
		caricaCategoriaAJAX(uri,e,xhr); 

} // caricaTesto()
