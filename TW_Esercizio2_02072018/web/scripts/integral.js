/*function myFunction()
{
	
	var text = document.getElementById("myText");
	var fileName = document.getElementById("fileName");
	
	
	
	if(text.value.length > 0 && fileName.value.length > 0)
		{
		
		// assegnazione oggetto XMLHttpRequest
			var	xhr = myGetXmlHttpRequest();
			var xhr2 = myGetXmlHttpRequest();
			if ( xhr ) 
				countingAJAX(xhr, xhr2, fileName.value, text.value); 
			else 
				countingIframe(); 
		}else
			{
				alert("the inputs text cannot be empty!");		
			}
	
		

}*/

var ris1 =-1;
var ris2 =-1;
var ris3 =-1;
var ris4 =-1;
var requestCounter = 0;
var sessionStartTime = -1;
var lastCall = -1;


function counter( jsonText ) {
   
		if(ris1== -1)
			{
				ris1= jsonText;
			}
		else
			{
				if(ris2 == -1)
					{
						ris2 = jsonText;
					}
				else
					{
						if(ris3 == -1)
							{
								ris3 = jsonText;
							}
						else
							{
								ris4 = jsonText;
								var totalresult = ris1 + ris2 + ris3 + ris4;
								var element = document.getElementById("result");
								element.value = totalresult;
								ris1 = -1;
								ris2 = -1;
								ris3 = -1;
								ris4 = -1;
							}
					}
			}
		
		  

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








function integralframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	Alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



function integralAJAX( theXhr, theXhr2, theXhr3, theXhr4, a, b, step) {
    
	

	// impostazione controllo e stato della richiesta
	theXhr.onreadystatechange = function() { callback(theXhr); };
	theXhr2.onreadystatechange = function() { callback(theXhr); };
	theXhr3.onreadystatechange = function() { callback(theXhr); };
	theXhr4.onreadystatechange = function() { callback(theXhr); };
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		theXhr.open("post", "Integrale", true);
		theXhr2.open("post", "Integrale", true);
		theXhr3.open("post", "Integrale", true);
		theXhr4.open("post", "Integrale", true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	// rimozione dell'header "connection" come "keep alive"
	theXhr.setRequestHeader("connection", "close");
	theXhr2.setRequestHeader("connection", "close");
	theXhr3.setRequestHeader("connection", "close");
	theXhr4.setRequestHeader("connection", "close");
	
	theXhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr3.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	theXhr4.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	
	var argument = "a="+a+"&b="+step;
	// invio richiesta
	//	
	theXhr.send(argument);
	argument = "a="+(step+1)+"&b="+(2*step);
	// invio richiesta
	theXhr2.send(argument);
	
	argument = "a="+(2*step+1)+"&b="+(3*step);
	// invio richiesta
	theXhr3.send(argument);
	
	argument = "a="+(3*step+1)+"&b="+(b);
	// invio richiesta
	theXhr4.send(argument);

} // caricaFeedAJAX()

function closeSession()
{
	requestCounter = 10;
	lastCall = 60000;
	alert('Sessione scaduta');
}

function sessionCounter()
{
	sessionStartTime = new Date().getTime();
	lastCall = sessionStartTime;
	setTimeout(closeSession(), 1000*60*60);
}

function myFunction()
{
	
	
	
	var a = document.getElementById("a").value;
	var b = document.getElementById("b").value;
	var step = (b-a)/4;
	var actualCall = new Date().getTime();
	var current = Math.round(actualCall / (1000 * 60));
	var last = Math.round(lastCall / (1000 * 60));
	if(requestCounter >= 5 && (current - last)<=10)
		{
			lastCall = actualCall;
			requestCounter++;
		// assegnazione oggetto XMLHttpRequest
			var	xhr = myGetXmlHttpRequest();
			var xhr2 = myGetXmlHttpRequest();
			var	xhr3 = myGetXmlHttpRequest();
			var xhr4 = myGetXmlHttpRequest();
			if ( xhr ) 
				integralAJAX(xhr, xhr2, xhr3, xhr4, a, b, step); 
			else 
				integralIframe(); 
		}else
			{
				alert("sessione terminata");		
			}
	
		

}