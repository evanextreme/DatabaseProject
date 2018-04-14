package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;
import org.thymeleaf.util.DateUtils;

/**
 * Model for the Transaction table
 */
public class Transaction {

	/**
	 * Prints out all info for debugging application
	 * Assumes debugging record from database (so all required fields
	 * like id are present)
	 */
	public void debug() {
		System.out.println("Transaction #" + id);
		System.out.println(">\tCustomer: " + customer.getId());
		System.out.println(">\tStore: " + store.getId());
		System.out.println(">\tDate: " + date.toString());
		System.out.println(">\tTotal: $" + total);
		System.out.println(">\tReturned? " + isReturned());
	}
	
	// Private fields

	private int id;

	private Customer customer;

	private Store store;

	private Discount discount;

	private DateTime date;

	private double total;

	private Transaction originalTransaction;
	
	// Getter and setters for private fields

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Customer getCustomer(){
		return customer;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public Store getStore(){
		return store;
	}

	public void setStore(Store store){
		this.store = store;
	}

	public Discount getDiscount(){ return discount; }

	public void setDiscount(Discount discount){
		this.discount = discount;
	}

	public DateTime getDate(){
		return date;
	}

	public void setDate(DateTime date){
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isReturned() {
		return this.originalTransaction != null;
	}

	public Transaction getOriginalTransaction() {
		return originalTransaction;
	}

	public void setOriginalTransaction(Transaction originalTransaction) {
		this.originalTransaction = originalTransaction;
	}
}
