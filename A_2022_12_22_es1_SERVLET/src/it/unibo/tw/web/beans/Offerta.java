package it.unibo.tw.web.beans;

import java.io.Serializable;

public class Offerta implements Serializable {

	private static final long serialVersionUID = 1L;

	User utente;
	int deanro;
	String continente;
	
	
	// --- constructor ----------
	
	public Offerta() {
	}


	public User getUtente() {
		return utente;
	}


	public void setUtente(User utente) {
		this.utente = utente;
	}


	public int getDeanro() {
		return deanro;
	}


	public void setDeanro(int deanro) {
		this.deanro = deanro;
	}


	public String getContinente() {
		return continente;
	}


	public void setContinente(String continente) {
		this.continente = continente;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
