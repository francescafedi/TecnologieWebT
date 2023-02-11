package beans;

public class OperationResp {
	
	String parola;
	int result;
	
	
	
	
	@Override
	public String toString() {
		return "OperationResp [parola=" + parola + ", result=" + result + "]";
	}




	public String getParola() {
		return parola;
	}




	public void setParola(String parola) {
		this.parola = parola;
	}




	public int getResult() {
		return result;
	}




	public void setResult(int result) {
		this.result = result;
	}




	public OperationResp() {
		super();
		this.result = -1;
		this.parola = "";
	}
	
	
	

}
