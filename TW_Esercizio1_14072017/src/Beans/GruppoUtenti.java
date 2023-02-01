package Beans;

import java.util.*;

public class GruppoUtenti {
	
	
	private String id;
	private Set<User> utenti;
	private Cart carrello;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<User> getUtenti() {
		return utenti;
	}
	public void setUtenti(Set<User> utenti) {
		this.utenti = utenti;
	}
	public Cart getCarrello() {
		return carrello;
	}
	public void setCarrello(Cart carrello) {
		this.carrello = carrello;
	}
	public GruppoUtenti() {
		this.id ="";
		this.utenti = new HashSet<User>();
		this.carrello = null;
	}
	
	public boolean addUserToGroup(User u)
	{
		return this.utenti.add(u);
	}
	
	
	
	
	

}
