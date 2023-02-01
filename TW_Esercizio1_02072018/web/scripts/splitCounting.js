var counter =0;

function counter( jsonText ) {
   
	// variabili di funzione
	
	
		var conteggio = JSON.parse(jsonText);
		// Ottengo la lista delle sezioni del docuemento
		var element = myGetElementById("result");
		element.innerText = '<p>'+ conteggio.beanTime+' '+conteggio.serverTime+'</p>';
		element.innerText = element.innerText + '<p>'+ conteggio.beanCounter+' '+conteggio.serverCounter+'</p>';
		

}// parsificaJSON()





/*
 * Funzione di callback
 */
function callback( theXhr ) {

	
	if ( theXhr.readyState === 2 ) {
	    	//theElement.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    	//	theElement.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			counter(theXhr.responseText);
			
			//location.reload();
		}// if 200

		 else {
	        	// errore di caricamento
			 alert("Impossibile effettuare l'operazione richiesta.");
//	        	theElement.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
//	        	theElement.innerHTML += "Errore riscontrato: " + theXhr.statusText;
	        }// else (if ! 200)
	}// if 4

} // callback();



function countingframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	Alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



function countingAJAX( theXhr, elements) {
    
	
	var fileName1 = elements[0].value;
	var fileName2 = elements[1].value;
	var fileName3 = elements[2].value;
	
	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", "FileManager", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");
	
	var argument = "file1="+fileName1+"&file2="+fileName2+"&file3="+fileName3;
	// invio richiesta
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr.send(argument);
} // caricaFeedAJAX()

function myfunction(element)
{
	if(element.checked == true)
		{
			counter ++;
			if(counter == 3)
				{
					counter =0;
					elements = document.getElementsByTagName("input");
					var checkedElements;
					for(int i=0; i<elements.length; i++)
						{
							if(elements[i].checked == true)
								{
									checkedElements.add(elements[i]);
								}
						}
					var	xhr = myGetXmlHttpRequest();
					if ( xhr ) 
						countingAJAX(xhr, checkedElements); 
					else 
						countingIframe(); 
					// fai quello che devi fare
				}
		}
}
