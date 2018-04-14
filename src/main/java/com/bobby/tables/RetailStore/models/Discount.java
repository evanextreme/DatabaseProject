package com.bobby.tables.RetailStore.models;

/**
 * Model for the Discount Table
 */
public class Discount {

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

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getPercentage(){
		return percentage;
	}

	public void setPercentage(int name){
		this.percentage = name;
	}
}
