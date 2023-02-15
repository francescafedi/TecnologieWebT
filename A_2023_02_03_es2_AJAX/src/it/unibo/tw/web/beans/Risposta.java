package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Risposta implements Serializable {

	private static final long serialVersionUID = 1L;

	String result;
	
	
	public Risposta() {
		super();
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	
}
