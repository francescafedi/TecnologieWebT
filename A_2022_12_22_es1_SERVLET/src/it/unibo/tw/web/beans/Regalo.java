package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.List;

public class Regalo implements Serializable {

	private static final long serialVersionUID = 1L;

	String nome;
	int prezzo;
	String descrizione;
	String continente;
	boolean asta;
	String acquirente;
	List<Offerta> offerte;
	
	// --- constructor ----------
	
	public Regalo() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	

	public boolean isAsta() {
		return asta;
	}

	public void setAsta(boolean asta) {
		this.asta = asta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAcquirente() {
		return acquirente;
	}

	public void setAcquirente(String acquirente) {
		this.acquirente = acquirente;
	}

	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}

	// --- getters and setters --------------
	
	
}
