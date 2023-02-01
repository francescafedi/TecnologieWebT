const socket2 = new WebSocket("ws://localhost:8080/07_TecWeb/actions2");

function sendUpdate( data) {
    var json = JSON.stringify(data);

    socket2.send(json);
}

socket2.onmessage =  function (event){
	console.log(event);
	var message = JSON.parse(event.data);
		console.log(message);
		 	var op1 = document.getElementById("op1");
		 	if(message.op1!=0.0){
		    op1.value = message.op1;}
		 	var op2 = document.getElementById("op2");
		 	if(message.op2!=0.0){
		    op2.value = message.op2;
		 	}
		 	var ris = document.getElementById("risultato");
		 	if(message.risultato!=-1){
		    ris.value = message.risultato;
		 	}
}


function myFunctionOnChange(element)
{
	var updateReq= {};
	updateReq.update=element.name;
	updateReq.valore=element.value;
	
	sendUpdate(updateReq);
	
}
