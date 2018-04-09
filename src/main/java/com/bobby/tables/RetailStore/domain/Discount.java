package com.bobby.tables.RetailStore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "discount")
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "percentage", nullable = false)
	private int percentage;
	
	public Discount(){
		
	}

	
	@JsonView(Discount.class)
	public int getId(){
		return id;
	}
	@JsonView(Discount.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Discount.class)
	public int getPercentage(){
		return percentage;
	}
	@JsonView(Discount.class)
	public void setPercentage(int name){
		this.percentage = name;
	}
	
	public double amountOff(double price){
		return price * (percentage * 0.01);
	}
}
