package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;
import org.thymeleaf.util.DateUtils;

import java.sql.Date;

/**
 * 	Model for the Customer table
 */
public class Customer {

	public interface PublicView {}

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

	/**
	 * Prints out all info for debugging application
	 * Assumes debugging record from database (so all required fields
	 * like id are present)
	 */
	public void debug() {
		System.out.println("Customer #" + id);
		if (isFrequentShopper()) {
			System.out.println(">\tFirst Name: " + first);
			System.out.println(">\tLast Name: " + last);
			System.out.println(">\tEmail: " + email);
			System.out.println(">\tAddress: " + address);
			System.out.println(">\tGender: " + gender);
			System.out.println(">\tDate of Birth: " + dob.toString());
			System.out.println(">\tCredit card: " + creditCard);
		}
	}

	// Getter and setter methods for private fields

	public String getStringOrNullString(String string) {
		if (string == null) {
			return "null";
		}
		return string;
	}

	@JsonView(PublicView.class)
	public int getId(){
		return id;
	}
	@JsonView(PublicView.class)
	public void setId(int id){
		this.id = id;
	}

	@JsonView(PublicView.class)
	public String getFirstName(){
		return getStringOrNullString(first);
	}
	@JsonView(PublicView.class)
	public void setFirstName(String first){
		this.first = first;
	}

	@JsonView(PublicView.class)
	public String getLastName(){
		return getStringOrNullString(last);
	}
	@JsonView(PublicView.class)
	public void setLastName(String last){
		this.last = last;
	}

	@JsonView(PublicView.class)
	public String getEmail(){
		return getStringOrNullString(email);
	}
	@JsonView(PublicView.class)
	public void setEmail(String email){
		this.email = email;
	}

	@JsonView(PublicView.class)
	public String getPhoneNumber(){
		return getStringOrNullString(phoneNumber);
	}
	@JsonView(PublicView.class)
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	@JsonView(PublicView.class)
	public String getAddress(){
		return getStringOrNullString(address);
	}
	@JsonView(PublicView.class)
	public void setAddress(String address){
		this.address = address;
	}

	@JsonView(PublicView.class)
	public String getGender(){
		return getStringOrNullString(gender);
	}
	@JsonView(PublicView.class)
	public void setGender(String gender){
		this.gender = gender;
	}

	@JsonView(PublicView.class)
	public Date getDOB(){
		return dob;
	}
	@JsonView(PublicView.class)
	public void setDOB(Date dob){
		this.dob = dob;
	}

	@JsonView(PublicView.class)
	public String getCreditCard(){
		return getStringOrNullString(creditCard);
	}
	@JsonView(PublicView.class)
	public void setCreditCard(String creditCard){
		this.creditCard = creditCard;
	}

	@JsonView(PublicView.class)
	public boolean isFrequentShopper(){
		return frequentShopper;
	}
	@JsonView(PublicView.class)
	public void setFrequentShopper(boolean frequentShopper){
		this.frequentShopper = frequentShopper;
	}
}
