package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Risposta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String result;
	private int  count;
	private int maiuscole;
	private int count2;
	

	
	public int getCount2() {
		return count2;
	}


	public void setCount2(int count2) {
		this.count2 = count2;
	}


	public int getMaiuscole() {
		return maiuscole;
	}


	public void setMaiuscole(int maiuscole) {
		this.maiuscole = maiuscole;
	}


	public Risposta() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
