package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Attrazione> risposta;
	private char type;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}



	public List<Attrazione> getRisposta() {
		return risposta;
	}



	public void setRisposta(List<Attrazione> risposta) {
		this.risposta = risposta;
	}
	
	

	

}
