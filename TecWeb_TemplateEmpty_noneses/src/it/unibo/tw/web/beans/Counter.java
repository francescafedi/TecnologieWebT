package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;

	String textUpperCase;
	int count;
	int finale;
	
	
	public Counter() {
		super();
		textUpperCase=new String();
		count=0;
		finale=0;
		
	}

	public String getTextUpperCase() {
		return textUpperCase;
	}
	public void setTextUpperCase(String textUpperCase) {
		this.textUpperCase = textUpperCase.toUpperCase();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFinale() {
		return finale;
	}

	public void setFinale(int finale) {
		this.finale = finale;
	}
	
	
	
	

}
