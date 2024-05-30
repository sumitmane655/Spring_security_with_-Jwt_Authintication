package com.ecommerce.shopz.repository;

public class AuthReq {
	private String username;
	
	private String password;
	
	
	public AuthReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthReq(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	
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
