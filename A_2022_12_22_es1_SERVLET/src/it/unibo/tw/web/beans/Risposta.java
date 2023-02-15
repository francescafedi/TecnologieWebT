package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Risposta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String risposta;
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
	public Risposta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getRisposta() {
		return risposta;
	}



	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}


	
	

	

}
