package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;
	int risultato;
	int servlet;
	
	
	public Result() {
		super();
		risultato=0;
		servlet=0;
		
	}


	public int getRisultato() {
		return risultato;
	}


	public void setRisultato(int risultato) {
		this.risultato = risultato;
	}


	public int getServlet() {
		return servlet;
	}


	public void setServlet(int servlet) {
		this.servlet = servlet;
	}

	
}
