package it.unibo.tw.web.beans;

import java.io.Serializable;

public class Richiesta implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private char type;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Richiesta() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
