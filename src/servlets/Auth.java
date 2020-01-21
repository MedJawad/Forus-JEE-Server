package servlets;

import com.google.gson.annotations.Expose;

import beans.User;

public class Auth {

	@Expose private User user;
	@Expose private String sessionId;
	
	public Auth() {
		// TODO Auto-generated constructor stub
	}
	public Auth(User user,String sessionId) {
		this();
		this.user=user;
		this.sessionId=sessionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
