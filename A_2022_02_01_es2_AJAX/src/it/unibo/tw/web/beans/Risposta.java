package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Risposta implements Serializable {

	private static final long serialVersionUID = 1L;

	long tempoImpiegato;
	int numOccorenze;
	int id;
	
	
	public Risposta() {
		super();
	}


	public long getTempoImpiegato() {
		return tempoImpiegato;
	}


	public void setTempoImpiegato(long tempoImpiegato) {
		this.tempoImpiegato = tempoImpiegato;
	}


	public int getNumOccorenze() {
		return numOccorenze;
	}


	public void setNumOccorenze(int numOccorenze) {
		this.numOccorenze = numOccorenze;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	

}
