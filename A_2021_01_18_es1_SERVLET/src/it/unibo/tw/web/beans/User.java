package it.unibo.tw.web.beans;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	String username;
	String password;

		
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


}
