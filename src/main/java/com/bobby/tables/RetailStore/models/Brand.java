package com.bobby.tables.RetailStore.models;

/**
 * Model for the Brand table
 */
public class Brand {

	/** Default constructor */
	public Brand() {}

	/** Alternate constructor with fields */
	public Brand(int id, String name, String designer) {
		this.id = id;
		this.name = name;
		this.designer = designer;
	}

	/** Alternate constructor with fields */
	public Brand(String name, String designer) {
		this.name = name;
		this.designer = designer;
	}

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
