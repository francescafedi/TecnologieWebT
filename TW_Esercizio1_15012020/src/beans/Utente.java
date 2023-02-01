package beans;

import java.util.*;

public class Utente {
	private String nomeUtente;
	private String password;
	private String gruppo;
	private Date zeroTime;
	private boolean valid;
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGruppo() {
		return gruppo;
	}
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}
	public Date getZeroTime() {
		return zeroTime;
	}
	public void setZeroTime(Date zeroTime) {
		this.zeroTime = zeroTime;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
		
		this.zeroTime = new Date();
		this.valid = true;
	}
	@Override
	public String toString() {
		return "Utente [nomeUtente=" + nomeUtente + ", gruppo=" + gruppo + ", valid=" + valid + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Utente altroU = (Utente) obj;
		if(this.nomeUtente.compareTo(altroU.getNomeUtente())==0 && this.gruppo.compareTo(altroU.getGruppo())==0)
			return true;
		else
			return false;
	}
	
	public boolean isStillValid()
	{
		if((this.getZeroTime().getTime() - new Date().getTime())>= 60000*24*60)
		{
			this.setValid(false);
			return false;
		}
		else
		{
			this.setValid(true);
			return true;
		}
	}
	
	
	
	
	
	
	
	
}
