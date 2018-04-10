package com.bobby.tables.RetailStore.models;

/**
 * Model for the Vendor table
 */
public class Vendor {
	
	// Private fields

	private int id;

	private String name;

	private String email;

	// Getters and setters for private fields

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
