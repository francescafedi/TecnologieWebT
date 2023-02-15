package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Risposta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeCanzone;
	private long lunghezza;
	private String formato;
	private byte[] contenuto;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getNomeCanzone() {
		return nomeCanzone;
	}


	public void setNomeCanzone(String nomeCanzone) {
		this.nomeCanzone = nomeCanzone;
	}


	public long getLunghezza() {
		return lunghezza;
	}


	public void setLunghezza(long lunghezza) {
		this.lunghezza = lunghezza;
	}


	public String getFormato() {
		return formato;
	}


	public void setFormato(String formato) {
		this.formato = formato;
	}


	public byte[] getContenuto() {
		return contenuto;
	}


	public void setContenuto(byte[] contenuto) {
		this.contenuto = contenuto;
	}


	public Risposta() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	

}
