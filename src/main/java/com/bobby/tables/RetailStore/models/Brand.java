package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model for the Brand table
 */
public class Brand {

	public interface PublicView {}

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
	public String getDesigner(){
		return designer;
	}
	@JsonView(PublicView.class)
	public void setDesigner(String designer){
		this.designer = designer;
	}

}
