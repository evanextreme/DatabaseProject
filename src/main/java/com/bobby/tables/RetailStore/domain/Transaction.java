package com.bobby.tables.RetailStore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;
	
	@JoinColumn(name = "discount_id", nullable = true)
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Discount discount;
	
	@Column(name = "date", nullable = false)
	private String date;
	
	public Transaction(){
		
	}

	@JsonView(Transaction.class)
	public int getId(){
		return id;
	}
	@JsonView(Transaction.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Transaction.class)
	public Customer getCustomer(){
		return customer;
	}
	@JsonView(Transaction.class)
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	@JsonView(Transaction.class)
	public Store getStore(){
		return store;
	}
	@JsonView(Transaction.class)
	public void setStore(Store store){
		this.store = store;
	}
	
	@JsonView(Transaction.class)
	public Discount getDiscount(){
		return discount;
	}
	@JsonView(Transaction.class)
	public void setDiscount(Discount discount){
		this.discount = discount;
	}
	
	@JsonView(Transaction.class)
	public String getDate(){
		return date;
	}
	@JsonView(Transaction.class)
	public void setDate(String date){
		this.date = date;
	}
}
