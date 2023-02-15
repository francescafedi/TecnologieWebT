package beans;

import java.util.List;
import java.util.Set;

import javax.websocket.Session;

public class OperationResp {
	
	int numero;
	int result;
	Session[] giocatori;
	
	
	
	
	@Override
	public String toString() {
		return "OperationResp [parola=" + numero + ", result=" + result + "]";
	}






	public Session[] getGiocatori() {
		return giocatori;
	}






	public void setGiocatori(Session[] giocatori) {
		this.giocatori = giocatori;
	}






	public int getNumero() {
		return numero;
	}






	public void setNumero(int numero) {
		this.numero = numero;
	}






	public int getResult() {
		return result;
	}




	public void setResult(int result) {
		this.result = result;
	}




	public OperationResp() {
		super();
		this.result = -1;
		this.numero = 0;
	}
	
	
	

}
