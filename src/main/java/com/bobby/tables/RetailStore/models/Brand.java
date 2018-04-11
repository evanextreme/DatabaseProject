package com.bobby.tables.RetailStore.models;

import com.bobby.tables.RetailStore.repository.BrandDAO;

import java.util.*;
import java.sql.*;
/**
 * Model for the Brand table
 */
public class Brand implements BrandDAO {

	// Private fields

	// ID associated with the brand
	private int id;

	// Brand Name
	private String name;

	private String designer;
	
	// Getters and setters for private fields

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

	public String getDesigner(){
		return designer;
	}

	public void setDesigner(String designer){
		this.designer = designer;
	}

	public ArrayList<Integer> getAllIds(){
		String idList = "SELECT id FROM brand";
		ArrayList<Integer> ids = new ArrayList<>();
		try{
			Connection conn = DriverManager.getConnection("classpath:/webjars/"); //get connection from h2 path
			Statement state = conn.createStatement(); //make a statement
			ResultSet list = state.executeQuery(idList); //statement used to execute query
			//loop through result set, add to list
			int i = 0;
			while(list.next()){
				int x = list.getInt(i);
				ids.add(x);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public ArrayList<String> getNames(){
		String nameList = "SELECT name FROM brand";
		ArrayList<String> names = new ArrayList<>();
		try{
			Connection conn = DriverManager.getConnection("classpath:/webjars/");
			Statement state = conn.createStatement();
			ResultSet list = state.executeQuery(nameList);
			int i = 0;
			while(list.next()){
				String x = list.getNString(i);
				names.add(x);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return names;
	}

	public ArrayList<String> getDesigners(){
		String designerList = "SELECT designer FROM brand";
		ArrayList<String> designers = new ArrayList<>();
		try{
			Connection conn = DriverManager.getConnection("classpath:/webjars/");
			Statement state = conn.createStatement();
			ResultSet list = state.executeQuery(designerList);
			int i = 0;
			while(list.next()){
				String x = list.getNString(i);
				designers.add(x);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return designers;
	}

	public void deleteCol(String col){
		String delete = "ALTER TABLE brand DROP COLUMN " + col;
		try{
			Connection conn = DriverManager.getConnection("classpath:/webjars/");
			Statement state = conn.createStatement();
			state.executeQuery(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//not sure what to implement here
	public void updateColId(){

	}
	public void updateColName(){

	}
	public void updateColDesigner(){

	}

}
