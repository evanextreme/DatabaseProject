package com.bobby.tables.RetailStore.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for the Vendor table
 */
public class Vendor {

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

	public static List<Vendor> fromResultSet(ResultSet resultSet) {
		List<Vendor> vendors = new ArrayList<>();
		try {
			while (resultSet.next()) {
				vendors.add(new Vendor(resultSet.getInt(1),
						resultSet.getString(2),
						resultSet.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendors;
	}
	
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
