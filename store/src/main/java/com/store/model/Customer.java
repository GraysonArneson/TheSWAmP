package com.store.model;

public class Customer {
	private String fname;
	private String lname;
	private String username;
	private String email;

	public Customer() {

	}

	public Customer(String username) {
		this.username = username;
	}

	public Customer(String fname, String lname, String username, String email) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{" +
				"\"fname\": \"" + fname + '\"' +
				", \"lname\": \"" + lname + '\"' +
				", \"username\": \"" + username + '\"' +
				", \"email\": \"" + email + '\"' +
				'}';
	}
}
