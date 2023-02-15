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



var risBackground;
var start=false;
var xOld=0, yOld=0;



function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};


function parsificaJson( json ) { //Testo
	var itemNodes=JSON.parse(json);
	console.log(itemNodes.risposta);
	var myRes="";

	
	if(itemNodes.type=="a"){
		var myRes = "Risultato:<br>";
		for(var i=0;i<itemNodes.risposta.length;i++){
			myRes+=itemNodes.risposta[i].nome+', '+itemNodes.risposta[i].descrizione+', '+itemNodes.risposta[i].posX+', '+itemNodes.risposta[i].posY+'<br>';
		}
		chiamaBackground();
		return myRes;
	}
	if(itemNodes.type=="b"){
		for(var i=0;i<itemNodes.risposta.length;i++){
			risBackground = "Risultato:<br>";
			risBackground+=itemNodes.risposta[i].nome+', '+itemNodes.risposta[i].descrizione+', '+itemNodes.risposta[i].posX+', '+itemNodes.risposta[i].posY+'<br>';
		}
		return myRes;
	}


}// parsificaJson()

/*
 * Funzione di callback
 */
function callback( theXhr, theElement ) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";
	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	   
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			theElement.innerHTML+=parsificaJson(theXhr.responseText);
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
function caricaFeedIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function caricaFeedAJAX(theUri, theElement, jsonRequest, theXhr) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement); };
	
	try {
		theXhr.open("post", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	//theXhr.setRequestHeader("connection", "close");

	// invio richiesta
	theXhr.send(jsonRequest);
} // caricaFeedAJAX()



/*	RICHIESTA
	String matrice1;
	String matrice2;
	int numRighe;
	int numColonne;
	int id;
 */
function caricaFeed(uri,e) {
	var data= {};
	var x=document.getElementById("x").value;
	var y=document.getElementById("y").value;
	if(x==xOld+50 && y==yOld && start==true){
		e.innerHTML=risBackground;
	}else{
	start=true;
	yOld=parseInt(y);
	xOld=parseInt(x);
    data.x=x;
    data.y=y;
    data.type="a";
    var jsonRequest;
	var	xhr= myGetXmlHttpRequest();
	jsonRequest=JSON.stringify(data);
	console.log(jsonRequest);
	if ( xhr ) 
		caricaFeedAJAX(uri,e, jsonRequest,xhr); 
	else 
		caricaFeedIframe(uri,e); 
	}
}// caricaFeed()



function chiamaBackground() {
	var data= {};
    data.x=xOld+50;
    data.y=yOld;
    data.type="b";
    var jsonRequest;
	var	xhr= myGetXmlHttpRequest();
	jsonRequest=JSON.stringify(data);
	console.log(jsonRequest);
	if ( xhr ) 
		caricaFeedAJAX("dis",myGetElementById('result'), jsonRequest,xhr); 
	else 
		caricaFeedIframe("dis",myGetElementById('result')); 
}
// caricaFeed()
