package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.List;

public class Asta implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Regalo> regali;
	boolean avviata;
	
	// --- constructor ----------
	
	public Asta() {
	}

	public List<Regalo> getRegali() {
		return regali;
	}

	public void setRegali(List<Regalo> regali) {
		this.regali = regali;
	}

	public boolean isAvviata() {
		return avviata;
	}

	public void setAvviata(boolean avviata) {
		this.avviata = avviata;
	}


	
}
