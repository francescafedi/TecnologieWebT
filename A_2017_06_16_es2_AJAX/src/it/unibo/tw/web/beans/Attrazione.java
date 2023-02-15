package it.unibo.tw.web.beans;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Attrazione implements Serializable {

	private static final long serialVersionUID = 1L;

	String nome;
	String descrizione;
	int posX;
	int posY;
		
	// --- constructor ----------
	
	public Attrazione() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// --- getters and setters --------------
	
	

}
