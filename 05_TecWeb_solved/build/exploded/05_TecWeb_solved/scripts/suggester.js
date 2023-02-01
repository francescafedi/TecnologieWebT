/*
 * Funzione di callback
 */
function categoriaCallback( theXhr, theElement ) {

	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
    	// non faccio niente
    	// theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	// non faccio niente
		// theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {

		// verifica della risposta da parte del server
	        if ( theXhr.status === 200 ) {

	        	// operazione avvenuta con successo
	
		        if ( theXhr.responseText && theXhr.responseText !== "" ) {
				        theElement.value = theXhr.responseText;
				}
	
				else {
			    	// non faccio niente
				}

	        }

	        else {
	        	// errore di caricamento
	        	// non faccio niente nemmeno qui
	        }

	}// if 4

} // categoriaCallback();



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX; per browser legacy
 */
function caricaCategoriaIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsarlo! è il browser che renderizza l'src dell'iframe!
}// caricaCategoriaIframe()



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaCategoriaAJAX(theUri, theElement, theXhr) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { categoriaCallback(theXhr, theElement); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);

} // caricaCategoriaAJAX()



/*
 * Scarica un contenuto testuale dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function caricaCategoria(uri,e) {

	// assegnazione oggetto XMLHttpRequest
	var xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		caricaCategoriaAJAX(uri,e,xhr); 
	else 
		caricaCategoriaIframe(uri,e); 

}// caricaFeed()
