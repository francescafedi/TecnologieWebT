package it.unibo.tw.web.beans;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	String username;
	String password;
	String group;
	boolean finalize=false;
	boolean login=false;
	private HttpServletRequest session;
	
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isFinalize() {
		return finalize;
	}

	public void setFinalize(boolean finalize) {
		this.finalize = finalize;
	}

	public HttpServletRequest getSession() {
		return getSession();
	}

	public void setSession(HttpServletRequest session) {
		this.session = session;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
	
	
	

}
