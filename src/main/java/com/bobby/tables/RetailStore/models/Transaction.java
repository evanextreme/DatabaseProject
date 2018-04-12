package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Model for the Transaction table
 */
public class Transaction {
	
	// Private fields

	private int id;

	// Foreign key reference for db
	private int customerId;

	// Navigation Property for application
	private Customer customer;

	// Foreign key reference for db
	private int storeId;

	// Navigation Property for application
	private Store store;

	private int discountId;

	private Discount discount;

	private DateTime date;

	private int quantityOfItem;

	// Foreign key reference for db
	private int productId;

	// Navigation Property for application
	private Product product;

	private double total;
	
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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Store getStore(){
		return store;
	}

	public void setStore(Store store){
		this.store = store;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getDiscountId() {
		return discountId;
	}

	public void setDiscountId(int discountId) {
		this.discountId = discountId;
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

	public int getQuantityOfItem() {
		return quantityOfItem;
	}

	public void setQuantityOfItem(int quantityOfItem) {
		this.quantityOfItem = quantityOfItem;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
