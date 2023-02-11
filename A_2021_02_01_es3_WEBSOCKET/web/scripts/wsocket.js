const socket = new WebSocket("ws://localhost:8080/A_2021_02_01_es3_WEBSOCKET/actions");

function send( data) {
    var json = JSON.stringify(data);

    socket.send(json);
}

socket.onopen= function (event){
	alert("connesione aperta");
}

socket.onmessage =  function (event){
	
	 var message = JSON.parse(event.data);
	 if(message.result>0)
		 {
		 	var log = document.getElementById("parola");
			log.value = "";
		    console.log(event.data);
		   
		    log.value = message.parola;
		    if(message.result==5){
				 alert("Gioco finito");
		    }
		    
		 }else{
			 if(message.result==-1){
				 alert("Non ci sono abbastanza giocatori");
			 }else if(message.result==-2){
				 alert("Partita già iniziata");
			 }
			
		 }
	 	
}

function myFunction() //Start Game
{
	var op1 = document.getElementById("parola").value;	
	var operationReq={};
	operationReq.parola=op1;
	if (op1!=null){
	send(operationReq);
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