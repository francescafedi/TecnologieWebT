package it.unibo.tw.web.pojo;

import java.io.Serializable;

public class CalculationResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private double _number;
	private String _operation;
	private String _result;
	
	public CalculationResult(String number, String operation) {
		super();
		this._number = Double.parseDouble(number);
		this._operation = operation;
		this._result = this.calculate();
	}
	
	public String calculate() {
		switch(this._operation) {
		  case "log_e":
			  return Math.log(this._number) + "";
		  case "sqrt":
			  return Math.sqrt(this._number) + "";
		  case "e_x":
			  return Math.exp(this._number) + "";
		  case "1/x":
			  return (1/this._number) + "";    
		  default:
		    return "ERROR";
		}
	}
	
}
