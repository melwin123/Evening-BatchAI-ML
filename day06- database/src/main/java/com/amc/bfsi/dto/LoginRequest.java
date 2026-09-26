package com.amc.bfsi.dto;

public class LoginRequest {
	private String username;
	private String password;

	// 1. Mandatory no-arg constructor for JSON deserialization
	public LoginRequest() {
	}

	// 2. Getters and Setters (Crucial to prevent null binding)
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