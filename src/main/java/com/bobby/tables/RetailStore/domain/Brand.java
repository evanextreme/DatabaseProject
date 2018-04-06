package com.bobby.tables.RetailStore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "brand")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "designer", nullable = false)
	private String designer;
	
	public Brand(){
		
	}

	
	@JsonView(Brand.class)
	public int getId(){
		return id;
	}
	@JsonView(Brand.class)
	public void setId(int id){
		this.id = id;
	}
	
	@JsonView(Brand.class)
	public String getName(){
		return name;
	}
	@JsonView(Brand.class)
	public void setName(String name){
		this.name = name;
	}
	
	@JsonView(Brand.class)
	public String getDesigner(){
		return designer;
	}
	@JsonView(Brand.class)
	public void setDesigner(String designer){
		this.designer = designer;
	}
}
