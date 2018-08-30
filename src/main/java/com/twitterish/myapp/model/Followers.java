package com.twitterish.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Followers")
public class Followers {

	private String Username;
	private String Follower;
	private Integer Id;

	public String getUsername() {
		return Username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	public String getFollower() {
		return Follower;
	}

	public void setFollower(String follower) {
		this.Follower = follower;
	}

}
