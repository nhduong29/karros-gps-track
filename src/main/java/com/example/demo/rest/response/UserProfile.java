package com.example.demo.rest.response;

public class UserProfile {

	private Long id;
	private String username;
	private String name;

	public UserProfile(Long id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
