package com.bobby.tables.RetailStore.models;

/**
 * Model for the store table
 */
public class Store {

	/** Default constructor */
	public Store() {}

	/** Alternate constructor with fields */
	public Store(int id, String phoneNumber, String address, String email) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}

	/** Alternate constructor with fields */
	public Store(String phoneNumber, String address, String email) {
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}
	
	// Private fields

	private int id;

	private String phoneNumber;

	private String address;

	private String email;
	
	// Getter and setter methods for private fields

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
