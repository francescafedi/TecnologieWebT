/*
 * Funzione di callback
 */
function requestServerCalculation_callback( theXhr, callback ) {

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
		    	    callback(JSON.parse(theXhr.responseText)._result);
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
function requestServerCalculationIframe(theUri,  number, operation, theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsarlo! è il browser che renderizza l'src dell'iframe!
}// requestServerCalculationIframe()



/*
 * Imposta il contenuto testuale disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function requestServerCalculationAJAX(theUri, theXhr,  number, operation, callback) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { requestServerCalculation_callback(theXhr, callback); };

	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("get", `${theUri}?number=${number}&operation=${operation}`, true);
        //theXhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// invio richiesta
	//theXhr.send(JSON.stringify({number, operation}));
	theXhr.send();

} // requestServerCalculationAJAX()



/*
 * Scarica un contenuto testuale dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function requestServerCalculation(uri, number, operation, callback) {

	// assegnazione oggetto XMLHttpRequest
	var xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		requestServerCalculationAJAX(uri, xhr,  number, operation, callback); 
	else 
		requestServerCalculationIframe(uri, number, operation); 

}// caricaFeed()
