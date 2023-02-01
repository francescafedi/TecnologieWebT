package beans;
import java.util.*;


public class GruppoUtenti {
	
	private List<Utente> utenti;
	private String nomeGruppo;
	public List<Utente> getUtenti() {
		return utenti;
	}
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	public String getNomeGruppo() {
		return nomeGruppo;
	}
	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}
	public GruppoUtenti() {
		this.utenti = new ArrayList<Utente>();
	}
	
	public void addUtente(Utente u)
	{
		if( u == null)
			return;
		
		if(this.utenti.contains(u))
		{
			this.utenti.remove(u);
			this.utenti.add(u);
		}
			
	}
	
	public void removeUtente(Utente u)
	{
		this.utenti.remove(u);
	}
	
	public boolean containsUser(Utente u)
	{
		return this.utenti.contains(u);
	}
	
	public Utente getUtenteByName(String userName)
	{
		if(userName.isEmpty() || userName == null)
			return null;
		for(Utente u : this.utenti)
		{
			if(u.getNomeUtente().compareTo(userName)==0)
				return u;
		}
		return null;
	}
	
	public int checkValidity()
	{
		int result =0;
		for(Utente u : this.utenti)
		{
			if(!u.isStillValid())
			{
				result++;
			}
		}
		return result;
	}
	
	
	
	
	
	

}
