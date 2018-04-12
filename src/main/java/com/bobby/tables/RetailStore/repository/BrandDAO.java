package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Brand;

import java.sql.*;
import java.util.*;

public class BrandDAO {

    public static DatabaseConnection conn;

    public BrandDAO(DatabaseConnection connection){
        this.conn = connection;
    }



    public static List<Brand> getAllBrands(){
        String s = "SELECT * FROM brand;";
        ArrayList<Brand> brands = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                Brand b = new Brand();
                b.setId(list.getInt(1));
                b.setName(list.getNString(2));
                b.setDesigner(list.getNString(3));
                brands.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    public static ArrayList<Integer> getAllids(){
        String idList = "SELECT id FROM brand;";
        ArrayList<Integer> ids = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement(); //make a statement
            ResultSet list = state.executeQuery(idList); //statement used to execute query
            //loop through result set, add to list
            while(list.next()){
                ids.add(list.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static ArrayList<String> getNames(){
        String nameList = "SELECT name FROM brand;";
        ArrayList<String> names = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(nameList);
            while(list.next()){
                names.add(list.getNString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static ArrayList<String> getDesigners(){
        String designerList = "SELECT designer FROM brand;";
        ArrayList<String> designers = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(designerList);
            while(list.next()){
                designers.add(list.getNString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return designers;
    }

    public static void deleteCol(String col){
        String delete = "ALTER TABLE brand DROP COLUMN " + col + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBrand(Brand bran){
        String update = "Select * FROM brand WHERE brand.id = " + bran.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBrand(Brand bran){
        String add = "INSERT INTO brand (name, designer) VALUES ('" + bran.getName() + "', '" + bran.getDesigner() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}