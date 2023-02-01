package Beans;

import javax.servlet.http.HttpSession;

public class User {
	
	private String userName;
	private String pwd;
	private String groupId;
	private HttpSession session;
	private boolean finalized;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public User() {
		
		// TODO Auto-generated constructor stub
		this.session = null;
		this.groupId = "";
		this.userName = "";
		this.pwd = "";
		this.finalized = false;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", pwd=" + pwd + ", groupId="
				+ groupId + "]";
	}
	@Override
	public boolean equals(Object obj) {
		User u = (User)obj;
		return this.userName.equals(u.getUserName());
		// TODO Auto-generated method stub
	
	}
	public boolean isFinalized() {
		return finalized;
	}
	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}
	
	
	
	
	
	

}
