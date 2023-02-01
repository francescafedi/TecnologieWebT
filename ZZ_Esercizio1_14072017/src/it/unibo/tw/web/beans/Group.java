package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Group implements Serializable {
	String groupName;
	Set<User> utenti;
	Cart cart;
	boolean finalizeGlobal;
	
	
	public Group() {
		super();
		this.utenti = new HashSet<User>();
		cart=new Cart();
		finalizeGlobal=false;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Set<User> getUtenti() {
		return utenti;
	}
	public void setUtenti(User ut) {
		this.utenti.add(ut);
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public boolean isFinalizeGlobal() {
		return finalizeGlobal;
	}
	public void setFinalizeGlobal(boolean finalizeGlobal) {
		this.finalizeGlobal = finalizeGlobal;
	}
	
	
}
