package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the Vendor table
 */
public class Vendor {

	public interface PublicView {}

	/** Default constructor */
	public Vendor() {}

	/** Alternate constructor with fields */
	public Vendor (int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/** Alternate constructor with fields */
	public Vendor (String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * Prints out all info for debugging application
	 * Assumes debugging record from database (so all required fields
	 * like id are present)
	 */
	public void debug() {
		System.out.println("Vendor #" + id);
		System.out.println(">\tName: " + name);
		System.out.println(">\tEmail: " + email);
	}

	// Private fields

	private int id;

	private String name;

	private String email;

	// Getters and setters for private fields
	@JsonView(PublicView.class)
	public int getId(){
		return id;
	}
	@JsonView(PublicView.class)
	public void setId(int id){
		this.id = id;
	}

	@JsonView(PublicView.class)
	public String getName(){
		return name;
	}
	@JsonView(PublicView.class)
	public void setName(String name){
		this.name = name;
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
