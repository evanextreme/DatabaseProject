package com.bobby.tables.RetailStore.models;

/**
 * Model for the Brand table
 */
public class Brand {

	// Private fields

	// ID associated with the brand
	private int id;

	// Brand Name
	private String name;

	private String designer;
	
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

	public String getDesigner(){
		return designer;
	}

	public void setDesigner(String designer){
		this.designer = designer;
	}
}
