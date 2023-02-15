package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Group implements Serializable {
	String groupId;
	Set<User> utenti;
	Cart cart;
	
	public Group() {
		super();
		this.utenti = new HashSet<User>();
		cart=new Cart();
		}
	public String getgroupId() {
		return groupId;
	}
	public void setgroupId(String groupId) {
		this.groupId = groupId;
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

	
}
