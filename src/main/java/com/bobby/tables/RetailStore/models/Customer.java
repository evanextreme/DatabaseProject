package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;

/**
 * 	Model for the Customer table
 */
public class Customer {

	public enum Gender {
		MALE, FEMALE;

		@Override
		public String toString() {
			if (this.equals(MALE)) {
				return "Male";
			} else {
				return "Female";
			}
		}
	}
	
	// Fields for customer data

	private int id;

	private String name;

	private String email;

	private String phoneNumber;

	private String address;

	private String gender;

	//TODO: Not use datetime? Java sql date?
	private DateTime dob;

	private String creditCard;

	private boolean frequentShopper;

	/**
	 * Gets the age of the customer
     */
	public int getAge() {
		return DateTime.now().getYearOfCentury() - dob.getYearOfCentury();
	}

	// Getter and setter methods for private fields

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getGender(){
		return gender;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public DateTime getDOB(){
		return dob;
	}

	public void setDOB(DateTime dob){
		this.dob = dob;
	}

	public String getCreditCard(){
		return creditCard;
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
