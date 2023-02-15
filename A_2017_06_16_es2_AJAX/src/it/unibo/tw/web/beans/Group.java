package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Group implements Serializable {
	String groupId;
	Set<Attrazione> utenti;
	Turista cart;
	
	public Group() {
		super();
		this.utenti = new HashSet<Attrazione>();
		cart=new Turista();
		}
	public String getgroupId() {
		return groupId;
	}
	public void setgroupId(String groupId) {
		this.groupId = groupId;
	}
	public Set<Attrazione> getUtenti() {
		return utenti;
	}
	public void setUtenti(Attrazione ut) {
		this.utenti.add(ut);
	}
	public Turista getCart() {
		return cart;
	}
	public void setCart(Turista cart) {
		this.cart = cart;
	}

	
}
