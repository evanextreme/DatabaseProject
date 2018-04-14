package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model for the Discount Table
 */
public class Discount {

	public interface PublicView {}

	/**
	 * Default public constructor
	 */
	public Discount() {}

	/** Alternate constructor with fields */
	public Discount(int id, int percentage) {
		this.id = id;
		this.percentage = percentage;
	}

	/** Alternate constructor with fields */
	public Discount(int percentage) {
		this.percentage = percentage;
	}
	
	// Private fields

	private int id;
	
	// Percent off of an item
	private int percentage;

	/**
	 * Returns the discounted price for an item
     */
	public double amountOff(double price){
		return price * (percentage * 0.01);
	}

	/**
	 * Prints out all info for debugging application
	 * Assumes debugging record from database (so all required fields
	 * like id are present)
	 */
	public void debug() {
		System.out.println("Discount #" + id);
		System.out.println(">\tPercent off: " + percentage + "%");
	}

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
	public int getPercentage(){
		return percentage;
	}
	@JsonView(PublicView.class)
	public void setPercentage(int name){
		this.percentage = name;
	}
}
