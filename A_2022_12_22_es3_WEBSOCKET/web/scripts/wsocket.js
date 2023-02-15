const socket = new WebSocket("ws://localhost:8080/A_2020_12_23_es3_WEBSOCKET/actions");

function send(data) {
	var json = JSON.stringify(data);
    socket.send(json);
}


socket.onmessage =  function (event){
	alert("File cambiato");
	/* var message = JSON.parse(event.data);
	 if(message.result>0)
		 {
		 if(message.result==1) alert("Iscritto alla partita");
		 if(message.result==2) alert("Partita avviata con successo");
		 	
		 }else{
			 if(message.result==-1) alert("Partita già iniziata");
			 if(message.result==-2) alert("Non è possibile avviare la partita");
				 
			 }
	 if(message.result==0){
		 var element= document.getElementById("result");
		 element.value=message.numero;
	 }
	 
	 if(message.result==100){
		 alert("Partita finita");
	 }
	 if(message.result==150){
		 var element= document.getElementById("result");
		 console.log(message);
	 }
			
	*/	
	 	
}

socket.onclose = function (event){
	alert("Uscito correttamente dal gioco");
}

function myFunction(action) //Start Game
{
	if(action!=null){
		var richiesta={};
		richiesta.action=action;
		if(richiesta.action=="login"){
			richiesta.username=document.getElementById("username").value;
		}
		if(richiesta.action=="leave"){
			socket.close();
		}else{		
		send(richiesta);
		}
	}
	
}

/*
function login() //Login Admin
{
	var op1 = document.getElementById("username").value;	
	var operationReq={};
	operationReq.type=op1;
	if (op1!=null){
	send(operationReq);
	}
}*/