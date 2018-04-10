package com.bobby.tables.RetailStore.models;

/**
 * Model for the Discount Table
 */
public class Discount {
	
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
