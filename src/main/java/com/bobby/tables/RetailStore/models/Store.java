package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model for the store table
 */
public class Store {

	public interface PublicView {}

	/** Default constructor */
	public Store() {}

	/** Alternate constructor with fields */
	public Store(int id, String address, String phoneNumber, String email) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}

	/** Alternate constructor with fields */
	public Store(String address, String phoneNumber, String email) {
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}

	/**
	 * Prints out all info for debugging application
	 * Assumes debugging record from database (so all required fields
	 * like id are present)
	 */
	public void debug() {
		System.out.println("Store #" + id);
		System.out.println(">\tEmail: " + email);
		System.out.println(">\tAddress: " + address);
		System.out.println(">\tPhone Number: " + phoneNumber);
	}
	
	// Private fields

	private int id;

	private String phoneNumber;

	private String address;

	private String email;
	
	// Getter and setter methods for private fields
	@JsonView(PublicView.class)
	public int getId(){
		return id;
	}
	@JsonView(PublicView.class)
	public void setId(int id){
		this.id = id;
	}

	@JsonView(PublicView.class)
	public String getPhoneNumber(){
		return phoneNumber;
	}
	@JsonView(PublicView.class)
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	@JsonView(PublicView.class)
	public String getAddress(){
		return address;
	}
	@JsonView(PublicView.class)
	public void setAddress(String address){
		this.address = address;
	}

	@JsonView(PublicView.class)
	public String getEmail() {
		return email;
	}
	@JsonView(PublicView.class)
	public void setEmail(String email) {
		this.email = email;
	}
}
