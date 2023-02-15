package it.unibo.tw.web.beans;

import java.io.Serializable;

public class FileServer implements Serializable {

	private static final long serialVersionUID = 1L;

	String nome;
	boolean scaricato;
	
	// --- constructor ----------
	
	public FileServer() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isScaricato() {
		return scaricato;
	}

	public void setScaricato(boolean scaricato) {
		this.scaricato = scaricato;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
