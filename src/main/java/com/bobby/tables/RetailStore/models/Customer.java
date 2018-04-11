package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;

import java.util.Date;

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

	private String first;

	private String last;

	private String email;

	private String phoneNumber;

	private String address;

	private String gender;

	//TODO: Not use datetime? Java sql date?
	private Date dob;

	private String creditCard;

	private boolean frequentShopper;

	/**
	 * Gets the age of the customer
     */
	//public int getAge() {
	//	return DateTime.now().getYearOfCentury() - dob.getYearOfCentury();
	//}

	// Getter and setter methods for private fields

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getFirstName(){
		return first;
	}

	public void setFirstName(String first){
		this.first = first;
	}

	public String getLastName(){
		return last;
	}

	public void setLastName(String last){
		this.last = last;
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

	public Date getDOB(){
		return dob;
	}

	public void setDOB(Date dob){
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
