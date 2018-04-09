package com.bobby.tables.RetailStore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "store")
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	public Store(){
		
	}

	
	@JsonView(Store.class)
	public int getId(){
		return id;
	}
	@JsonView(Store.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Store.class)
	public String getPhoneNumber(){
		return phoneNumber;
	}
	@JsonView(Store.class)
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	@JsonView(Store.class)
	public String getAddress(){
		return address;
	}
	@JsonView(Store.class)
	public void setAddress(String address){
		this.address = address;
	}
}
