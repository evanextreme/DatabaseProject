package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.thymeleaf.util.DateUtils;

/**
 * Model for the Transaction table
 */
public class Transaction {

	public interface PublicView {}

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
	@JsonView(PublicView.class)
	public int getId(){
		return id;
	}
	@JsonView(PublicView.class)
	public void setId(int id){
		this.id = id;
	}

	@JsonView(PublicView.class)
	public Customer getCustomer(){
		return customer;
	}
	@JsonView(PublicView.class)
	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	@JsonView(PublicView.class)
	public Store getStore(){
		return store;
	}
	@JsonView(PublicView.class)
	public void setStore(Store store){
		this.store = store;
	}

	@JsonView(PublicView.class)
	public Discount getDiscount(){ return discount; }
	@JsonView(PublicView.class)
	public void setDiscount(Discount discount){
		this.discount = discount;
	}

	@JsonView(PublicView.class)
	public DateTime getDate(){
		return date;
	}
	@JsonView(PublicView.class)
	public void setDate(DateTime date){
		this.date = date;
	}

	@JsonView(PublicView.class)
	public double getTotal() {
		return total;
	}
	@JsonView(PublicView.class)
	public void setTotal(double total) {
		this.total = total;
	}

	@JsonView(PublicView.class)
	public boolean isReturned() {
		return this.originalTransaction != null;
	}

	@JsonView(PublicView.class)
	public Transaction getOriginalTransaction() {
		return originalTransaction;
	}
	@JsonView(PublicView.class)
	public void setOriginalTransaction(Transaction originalTransaction) {
		this.originalTransaction = originalTransaction;
	}
}
