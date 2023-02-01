package beans;

public class OperationResp {
	
	double op1;
	double op2;
	String tipoOp;
	double risultato;
	double oldRis;
	boolean success;
	boolean valid;
	
	
	
	
	
	public double getOldRis() {
		return oldRis;
	}
	public void setOldRis(double oldRis) {
		this.oldRis = oldRis;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public double getOp1() {
		return op1;
	}
	public void setOp1(double op1) {
		this.op1 = op1;
	}
	public double getOp2() {
		return op2;
	}
	public void setOp2(double op2) {
		this.op2 = op2;
	}
	public String getTipoOp() {
		return tipoOp;
	}
	public void setTipoOp(String tipoOp) {
		this.tipoOp = tipoOp;
	}
	public double getRisultato() {
		return risultato;
	}
	public void setRisultato(double risultato) {
		this.risultato = risultato;
	}
	public OperationResp() {
		super();
		this.success = false;
		this.valid = true;
		this.op1 =0;
		this.op2 =0;
		this.tipoOp="add";
		this.risultato = -1;
		this.oldRis = -1;
	}
	@Override
	public String toString() {
		return "OperationResp [op1=" + op1 + ", op2=" + op2 + ", tipoOp=" + tipoOp + ", risultato=" + risultato
				+ ", success=" + success + " oldRis " + oldRis + " ]";
	}
	
	
	
	

}
