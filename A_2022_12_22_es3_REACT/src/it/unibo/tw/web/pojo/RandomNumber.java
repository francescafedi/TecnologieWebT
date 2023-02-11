package it.unibo.tw.web.pojo;

import java.io.Serializable;

public class RandomNumber implements Serializable {

	private static final long serialVersionUID = 1L;

	private int _number;
	
	public RandomNumber(int number) {
		super();
		this._number = number;
	}
	
	public int getNumber() {
		return _number;
	}
	public void setTitle(int new_number) {
		this._number = new_number;
	}
	
}
