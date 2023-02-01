package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	int count;
	long tempo;
	
	
	public Counter() {
		super();
		count=0;
		tempo=0;
		
	}

	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}
	
	
	
	

}
