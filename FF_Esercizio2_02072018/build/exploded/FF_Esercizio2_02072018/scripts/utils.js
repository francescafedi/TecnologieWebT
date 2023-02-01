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













////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var tot=0;
var numberSession=0;
var lastCall=new Date();
var firstCall=lastCall;
var ris1 =-1;
var ris2 =-1;
var ris3 =-1;
var ris4 =-1;


function leggiContenuto(item, nomeNodo) {
	return item.getElementsByTagName(nomeNodo).item(0).firstChild.nodeValue;
};


function parsificaJson( json ) { //Testo
	var itemNodes=JSON.parse(json);
	console.log(itemNodes);
	var myRes="";
		var myRes = itemNodes.risultato;
		tot=tot+itemNodes.risultato;
		if(ris1== -1)
		{
			ris1= myRes;
		}
	else
		{
			if(ris2 == -1)
				{
					ris2 = myRes;
				}
			else
				{
					if(ris3 == -1)
						{
							ris3 = myRes;
						}
					else
						{
							ris4 = myRes;
							var totalresult = ris1 + ris2 + ris3 + ris4;
							myRes = ris4 + "<br> IL RISULTATO FINALE: "+totalresult+"<br>";
							
							ris1 = -1;
							ris2 = -1;
							ris3 = -1;
							ris4 = -1;
						}
				}
		}
	
	  

	return myRes;

}// parsificaJson()

/*
 * Funzione di callback
 */
function callback( theXhr, theElement, e, type) {

	// designiamo la formattazione della zona dell'output
	theElement.class = "content";
	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    	e.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    		e.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
				theElement.innerHTML+= type+": Risultato "+parsificaJson(theXhr.responseText)+ "<br>";
			
			//parsificaJson(theXhr.responseText);
		}// if 200

		else {
			// errore di caricamento
			e.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
			e.innerHTML += "Errore riscontrato: " + theXhr.statusText;
		}// else (if ! 200)
	}// if 4

} // callback();



/*
 * Imposta il contenuto disponibile presso theUri
 * come src di un iframe all'interno dell'elemento theHolder del DOM
 * Non usa AJAX per browser legacy
 */
function calcoloIntegraleIframe(theUri,theHolder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	theHolder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// calcoloIntegraleIframe()



/*
 * Imposta il contenuto xml disponibile presso theUri
 * all'interno dell'elemento theHolder del DOM
 * Usa tecniche AJAX attrverso la XmlHttpRequest fornita in theXhr
 */
function calcoloIntegraleAJAX(theUri, theElement, theElementInfo,primo, secondo, theXhr, theXhr2, theXhr3, theXhr4) {
    
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr, theElement, theElementInfo, "zero"); };
	theXhr2.onreadystatechange = function() { callback(theXhr2, theElement,theElementInfo, "uno"); };
	theXhr3.onreadystatechange = function() { callback(theXhr3, theElement,theElementInfo, "due" ); };
	theXhr4.onreadystatechange = function() { callback(theXhr4, theElement,theElementInfo, "tre"); };
	
	//Richiesta di tipo post perchè i dati devono arrivare in formato json
	try {
		theXhr.open("post", theUri, true);
		theXhr2.open("post", theUri, true);
		theXhr3.open("post", theUri, true);
		theXhr4.open("post", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	var tot=(primo+secondo)/4;
	
	var argument=new Object();
	argument.p=parseInt(primo);
	argument.s=tot;
	console.log(argument);
	var a=JSON.stringify(argument);
	// invio richiesta
	theXhr.send(a);

	argument.p=argument.p+tot;
	argument.s+=tot;
	var b=JSON.stringify(argument);
	theXhr2.send(b);

	argument.p=argument.p+tot;
	argument.s+=tot;
	 var c=JSON.stringify(argument);
	theXhr3.send(c);

	argument.p=argument.p+tot;
	argument.s+=tot;
	var d=JSON.stringify(argument);
	theXhr4.send(d);

} // calcoloIntegraleAJAX()



/*
 * Scarica un contenuto xml dall'uri fornito
 * e lo aggiunge al contenuto dell'elemento e del dom
 * Gestisce sia AJAX che il mancato supporto ad AJAX 
 */
function calcoloIntegrale(uri,e, eInfo) {
	numberSession++;
	var actualCall=new Date();
	var diff=actualCall-lastCall; //differenza di inattività
	diff=diff*0.001;
	diff=diff/60; //Tempo in minuti
	console.log(diff);
	var timepass=actualCall-firstCall;
	timepass=timepass*0.001;
	timepass=timepass/60;
	//tempo trascorso da inizio sessione
	if(numberSession<5 && (diff)<10 && timepass<60){
		lastCall=actualCall;
	var primo=document.getElementById("primo").value;
	var secondo=document.getElementById("secondo").value;
	// variabili di funzione
	var	xhr = myGetXmlHttpRequest();
	var xhr2= myGetXmlHttpRequest();
	var xhr3= myGetXmlHttpRequest();
	var xhr4= myGetXmlHttpRequest();

	if ( xhr && xhr2 && xhr3 && xhr4 ) {
		calcoloIntegraleAJAX(uri, e, eInfo, primo, secondo, xhr, xhr2, xhr3, xhr4); 
	}else {
		calcoloIntegraleIframe(uri,e); 
	}
	}else{
		alert("Numero di richieste possibili o tempo superato!");
	}
}// calcoloIntegrale()


