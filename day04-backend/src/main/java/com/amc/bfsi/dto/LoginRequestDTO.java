package com.amc.bfsi.dto;

public class LoginRequestDTO {
	
	public LoginRequestDTO() {
		
	}
	
	public LoginRequestDTO(String Name, String password) {
		super();
		this.Name = Name;
		this.password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String Name;
	private String password;

}
