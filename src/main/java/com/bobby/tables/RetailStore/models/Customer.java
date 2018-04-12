package com.bobby.tables.RetailStore.models;

import java.sql.Date;

/**
 * 	Model for the Customer table
 */
public class Customer {

	// Private fields

	private int id;

	private String first;

	private String last;

	private String email;

	private String phoneNumber;

	private String address;

	private String gender;

	private Date dob;

	private String creditCard;

	private boolean frequentShopper;

	// Getter and setter methods for private fields

	public String getStringOrNullString(String string) {
		if (string == null) {
			return "null";
		}
		return string;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getFirstName(){
		return getStringOrNullString(first);
	}

	public void setFirstName(String first){
		this.first = first;
	}

	public String getLastName(){
		return getStringOrNullString(last);
	}

	public void setLastName(String last){
		this.last = last;
	}

	public String getEmail(){
		return getStringOrNullString(email);
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getPhoneNumber(){
		return getStringOrNullString(phoneNumber);
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getAddress(){
		return getStringOrNullString(address);
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getGender(){
		return getStringOrNullString(gender);
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public Date getDOB(){
		return dob;
	}

	public void setDOB(Date dob){
		this.dob = dob;
	}

	public String getCreditCard(){
		return getStringOrNullString(creditCard);
	}

	public void setCreditCard(String creditCard){
		this.creditCard = creditCard;
	}

	public boolean isFrequentShopper(){
		return frequentShopper;
	}

	public void setFrequentShopper(boolean frequentShopper){
		this.frequentShopper = frequentShopper;
	}
}
