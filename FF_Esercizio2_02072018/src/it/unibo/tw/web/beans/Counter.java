package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	int p;
	int s;	
	
	public Counter() {
		super();
		p=0;
		s=0;
		
	}

	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}


	public int getS() {
		return s;
	}


	public void setS(int s) {
		this.s = s;
	}
}
