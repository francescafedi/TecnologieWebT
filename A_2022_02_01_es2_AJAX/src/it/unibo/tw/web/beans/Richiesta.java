package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Richiesta implements Serializable {

	private static final long serialVersionUID = 1L;

	String parola;
	int conto; //indica quante servlet devono fare questo conteggio
	int id; //indica se è la prima, seconda,terza o quarta richiesta
	
	
	public Richiesta() {
		super();
	}


	public String getParola() {
		return parola;
	}


	public void setParola(String parola) {
		this.parola = parola;
	}


	public int getConto() {
		return conto;
	}


	public void setConto(int conto) {
		this.conto = conto;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



}
