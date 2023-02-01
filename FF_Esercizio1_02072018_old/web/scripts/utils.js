/**
 * from http://javascript.html.it/guide/leggi/95/guida-ajax/
 */
function myGetElementById(idElemento) {

	// elemento da restituire
	var elemento;

	// se esiste il metodo getElementById questo if sara'� 
	// diverso da false, null o undefined
	// e sara'� quindi considerato valido, come un true
	if ( document.getElementById )
		elemento = document.getElementById(idElemento);

	// altrimenti e' necessario usare un vecchio sistema
	else
		elemento = document.all[idElemento];

	// restituzione elemento
	return elemento;

} // myGetElementById()



/**
 * from http://www.e-time.it/topics/34-ajax/8-Richiamare%20l%5C'oggetto%20XmlHttpRequest
 */ 
function myGetXmlHttpRequest() {

	// variabili 
	var 
		// risultato 
		xhr = false,
		// opzioni activeX dal pi� nuovo al pi� vecchio
		activeXoptions = new Array( "Microsoft.XmlHttp", "MSXML4.XmlHttp", "MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp" );

	// primo tentativo come oggetto nativo
	try { 
		xhr = new XMLHttpRequest(); 
	} 
	catch (e) { 
		// non facciamo niente... semplicemente proviamo un altro modo
	}

	// successivi tentativi come oggetto activeX dal piu' al meno recente
	if ( ! xhr ) {
		var created = false; 
		for ( var i = 0 ; i < activeXoptions.length && !created ; i++ ) {
			try {
				// tentativo di creazione
				xhr = new ActiveXObject( activeXoptions[i] );
				// se la creazione non fallisce il codice del blocco try continua a essere eseguito
				created = true;
			} 
			catch (e) { 
				// non facciamo niente... semplicemente proviamo un altro modo
			} 
		} // for ( MSXML options )
	} // if ( ! nativo )

	// restituzione del risultato, eventualmente ancora false se il browser non supporta AJAX
	return xhr;

} // myGetXmlHttpRequest()



/*
 * from http://www.dominopower.com/issues/issue200004/howto002.html
 */
function myGetRequestParameter ( parameterName ) {

	// variabili
	// estraiamo i parametri di get dalla uri della pagina
	var queryString = window.top.location.search.substring(1);

	// Add "=" to the parameter name (i.e. parameterName=value)
	// torna utile nello split della query per cercare il parametro voluto
	var parameterName = parameterName + "=";
		
	if ( queryString.length > 0 ) {

		// Find the beginning of the string
		begin = queryString.indexOf ( parameterName );

		// If the parameter name is not found, skip it, otherwise return the value
		if ( begin != -1 ) {

			// Add the length (integer) to the beginning
			begin += parameterName.length;

			// Multiple parameters are separated by the "&" sign
			end = queryString.indexOf ( "&" , begin );
	
			if ( end == -1 ) {
				end = queryString.length
			}// if ( ! end )

			// Return the string (unescapes special characters such as & / = etc...)
			return unescape ( queryString.substring ( begin, end ) );
		} // if ( begin )

		// Return "null" if no parameter has been found
		return "null";

	} // if ( querystring )

}// myGetRequestParametr()



/*
 * Funzione per recuperare per nome l'elemento figlio di un elemento dato
 *
 * Non usa l'id (che deve essere unico nel DOM) ma il name 
 * per lasciare la possibilita' di avere piu' nodi con lo stesso name
 * ma figli di elementi diversi.
 *
 * Ad esempio per collocare piu' immagini di attesa nel documento, 
 * in caso di piu' richieste AJAX contemporanee
 */
function myGetChildByName( theElement, name ) {
	
	// analisi alla ricerca del nodo voluto
	if ( theElement.childNodes ){
		for ( var i = 0 ; i < theElement.childNodes.length ; i++ ){
			if ( theElement.childNodes[i].nodeName == name )
				return theElement.childNodes[i];
		}
	}

}// myGetChildByName()













///////////////////////////////////////////////////////////////////////////////////////


function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};


function parsificaJson( json ) { //Testo
	var itemNodes=JSON.parse(json);
	console.log(itemNodes);
	var myRes="";
		var myRes = "SERVLET: Il numero di caratteri maiuscoli: "+itemNodes.count +", il tempo impiegato: "+itemNodes.tempo +" nanosecondi.";
	return myRes;

}// parsificaJson()

/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";
	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    	theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    		theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			theElement.innerHTML=parsificaJson(theXhr.responseText);
			//parsificaJson(theXhr.responseText);
		}// if 200

		else {
			// errore di caricamento
			theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function contaCaratteriIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// contaCaratteriIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function contaCaratteriAJAX(theUri, theUri1,theElement, theXhr, theXhr2, data) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };
	theXhr2.onreadystatechange = function() { callback(theXhr2, theElement); };
	theUri=theUri+"?length="+data.length;
	// impostazione richiesta asincrona in GET
	// del file specificato
	for(var i=0;i<data.length;i++){
	theUri+="&p"+i+"="+data[i];
	}
	try {
		theXhr.open("get", theUri, true);
		theXhr2.open("get", theUri1, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(null);
	theXhr2.send(null);

} // contaCaratteriAJAX()



/*
 * Scarica un contenuto xml dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function contaCaratteri(uri, uri1,e) {
	var arr = numberOfSelected();
	if(arr.length>=3){
	// variabili di funzione
	var
		// assegnazione oggetto XMLHttpRequest
		xhr = myGetXmlHttpRequest();
	var xhr2= myGetXmlHttpRequest();

	if ( xhr ) 
		contaCaratteriAJAX(uri,uri1, e,xhr, xhr2, arr); 
	else 
		contaCaratteriIframe(uri,e); 
	}
}// contaCaratteri()

function numberOfSelected() {
	 let arr = [];
	 let checkboxes = document.querySelectorAll("input[type='checkbox']:checked");
	 for (let i = 0 ; i < checkboxes.length; i++) {
	  arr.push(checkboxes[i].value);
	 }
	 return arr;
	}
