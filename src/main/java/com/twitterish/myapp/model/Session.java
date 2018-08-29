package com.twitterish.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Session")
public class Session {
	
	private String Username;
    private String SessionToken;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	@Id
	@Column(name = "session_token")
	public String getSessionToken() {
		return SessionToken;
	}
	public void setSessionToken(String SessionToken) {
		SessionToken = SessionToken;
	}
 
}
