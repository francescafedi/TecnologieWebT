package beans;

public class OperationReq {
	
	double op1;
	double op2;
	String operazione;
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
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	public OperationReq() {
		super();
	}
	@Override
	public String toString() {
		return "OperationReq [op1=" + op1 + ", op2=" + op2 + ", operazione=" + operazione + "]";
	}
	
	

}
