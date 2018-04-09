package com.bobby.tables.RetailStore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "gender", nullable = true)
	private String gender;
	
	@Column(name = "dob", nullable = true) //TODO chance this to date type?
	private String dob;
	
	@Column(name = "credit_card", nullable = true)
	private String creditCard;
	
	@Column(name = "frequent_shopper", nullable = false)
	private String frequentShopper;
	
	public Customer(){
		
	}

	
	@JsonView(Customer.class)
	public int getId(){
		return id;
	}
	@JsonView(Customer.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Customer.class)
	public String getName(){
		return name;
	}
	@JsonView(Customer.class)
	public void setName(String name){
		this.name = name;
	}
	
	@JsonView(Customer.class)
	public String getEmail(){
		return email;
	}
	@JsonView(Customer.class)
	public void setEmail(String email){
		this.email = email;
	}
	
	@JsonView(Customer.class)
	public String getPhoneNumber(){
		return phoneNumber;
	}
	@JsonView(Customer.class)
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	@JsonView(Customer.class)
	public String getAddress(){
		return address;
	}
	@JsonView(Customer.class)
	public void setAddress(String address){
		this.address = address;
	}
	
	@JsonView(Customer.class)
	public String getGender(){
		return gender;
	}
	@JsonView(Customer.class)
	public void setGender(String gender){
		this.gender = gender;
	}
	
	@JsonView(Customer.class)
	public String getDOB(){
		return dob;
	}
	@JsonView(Customer.class)
	public void setDOB(String dob){
		this.dob = dob;
	}
	
	@JsonView(Customer.class)
	public String getCreditCard(){
		return creditCard;
	}
	@JsonView(Customer.class)
	public void setCreditCard(String creditCard){
		this.creditCard = creditCard;
	}
	
	@JsonView(Customer.class)
	public String getFrequentShopper(){
		return frequentShopper;
	}
	@JsonView(Customer.class)
	public void setFrequentShopper(String frequentShopper){
		this.frequentShopper = frequentShopper;
	}
}
