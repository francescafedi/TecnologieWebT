package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Prenotazione implements Serializable {

	private static final long serialVersionUID = 1L;

	private int numerocampo;
	private String tipologia;
	int datag;
	int orario;
	List<User> giocatori=new ArrayList<User>();;
	boolean definitiva;
	LocalDate dataoraprenotazione;

	public Prenotazione() {
		super();
	}

	public int getNumerocampo() {
		return numerocampo;
	}

	public void setNumerocampo(int numerocampo) {
		this.numerocampo = numerocampo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public int getDatag() {
		return datag;
	}

	public void setDatag(int datag) {
		this.datag = datag;
	}

	public int getOrario() {
		return orario;
	}

	public void setOrario(int orario) {
		this.orario = orario;
	}

	public boolean isDefinitiva() {
		return definitiva;
	}

	public void setDefinitiva(boolean definitiva) {
		this.definitiva = definitiva;
	}

	public List<User> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<User> giocatori) {
		this.giocatori = giocatori;
	}

	public LocalDate getDataoraprenotazione() {
		return dataoraprenotazione;
	}

	public void setDataoraprenotazione(LocalDate dataoraprenotazione) {
		this.dataoraprenotazione = dataoraprenotazione;
	}

	
	
}
