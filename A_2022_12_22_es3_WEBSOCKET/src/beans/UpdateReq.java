package beans;

public class UpdateReq {
	
	private String update;
	private double valore;
	public String getOp() {
		return update;
	}
	public void setOp(String op) {
		this.update = op;
	}
	public double getValore() {
		return valore;
	}
	public void setValore(double valore) {
		this.valore = valore;
	}
	@Override
	public String toString() {
		return "UpdateReq [op=" + update + ", valore=" + valore + "]";
	}
	public UpdateReq() {
		super();
	}
	
	

}
