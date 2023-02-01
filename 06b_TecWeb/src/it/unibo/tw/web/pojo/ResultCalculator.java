package it.unibo.tw.web.pojo;

import java.io.Serializable;

public class ResultCalculator implements Serializable {

	private static final long serialVersionUID = 1L;

	private double _number;
	private int valido;
	
	public ResultCalculator(double number,int valido) {
		super();
		this._number = number;
		this.valido=valido;
	}
	
	public double getNumber() {
		return _number;
	}
	
	public int getValido() {
		return valido;
	}
	
	
}
