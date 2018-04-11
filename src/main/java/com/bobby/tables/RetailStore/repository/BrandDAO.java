package com.bobby.tables.RetailStore.repository;

import java.sql.*;
import java.util.*;

public class BrandDAO {

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
	
	//-------------NOTE-----------------
	//IGNORE the code below, these are some examples of custom queries. JPA provides most of the basic ones tho so no need to stress about these until they are needed!!!
	//-------------NOTE-----------------
	
	//SELECT * FROM category WHERE category_id IS NULL
//	@Query("FROM Brand myBrand WHERE cat.id = 1")
//	public List<Brand> getAllBrands();
//	
//	@Query("FROM Category WHERE category_id = :category_id ORDER BY name ASC")//TODO sort categories and positions by 'popularity' rather than name
//	public List<Category> getChildCategories(@Param("category_id") int category_id);
//	
//	@Query("FROM Position WHERE category_id = :category_id ORDER BY name ASC")
//	public List<Position> getChildPositions(@Param("category_id") int category_id);

