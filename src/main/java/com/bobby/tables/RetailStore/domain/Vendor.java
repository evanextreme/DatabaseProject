package com.bobby.tables.RetailStore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "vendor")
public class Vendor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	public Vendor(){
		
	}

	
	@JsonView(Vendor.class)
	public int getId(){
		return id;
	}
	@JsonView(Vendor.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Vendor.class)
	public String getName(){
		return name;
	}
	@JsonView(Vendor.class)
	public void setName(String name){
		this.name = name;
	}
}
