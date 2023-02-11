package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;

	private int numerocampo;
	private String tipologia;
	int datag;
	int orario;

	public Request() {
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

	
	
}
