const socket = new WebSocket("ws://localhost:8080/A_2022_01_26_es2_WEBSOCKET/actions");

function send(data) {
	var json = JSON.stringify(data);
    socket.send(json);
}


socket.onmessage =  function (event){
	var message = JSON.parse(event.data);
	for(var i=0; i<document.getElementsByTagName("input").length;i++){
    	if(document.getElementsByTagName("input")[i].name==message.data){
    		document.getElementById(message.id).value=message.value;
    		
	    	return;
    		}
    	} 	
}

socket.onclose = function (event){
}

function myFunction(action) //Start Game
{
	var element=document.getElementById(action).value;
	if(element!=null){
		var numbers = /^[0-9]*$/;
		
		    if(!numbers.test(element))
		    {
		    	alert("Inserire solo numeri");
		    	return;
		    }
		    var count=0;
		    var matrix='';
		    for(var i=0; i<document.getElementsByTagName("input").length;i++){
		    	if(document.getElementsByTagName("input")[i].value==element && document.getElementsByTagName("input")[i].name!=action){
		    		document.getElementById(action).value='';
		    		alert("Numero già presente");
			    	return;
		    		}
		    	if(document.getElementsByTagName("input")[i].value!=''){
		    		count++;
		    		matrix+=document.getElementsByTagName("input")[i].value+' '
		    	}
		    	}
		    


		var richiesta={};
		richiesta.data=action;
		richiesta.value=element;
		send(richiesta);
		if(count==9){
			//Invio la richiesta tramite ajax
			caricaFeed(document.getElementById("result"), matrix);
		}
		
	}
	
}
