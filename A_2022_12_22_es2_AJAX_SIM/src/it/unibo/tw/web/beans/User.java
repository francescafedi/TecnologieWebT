package it.unibo.tw.web.beans;

import java.io.Serializable;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	String username;
	String password;
	String groupId;
		
	// --- constructor ----------
	
	public User() {
	}

	// --- getters and setters --------------
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String group) {
		this.groupId = group;
	}

}
