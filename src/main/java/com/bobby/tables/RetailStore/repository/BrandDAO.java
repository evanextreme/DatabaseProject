package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Brand;

import java.sql.*;
import java.util.*;

/**
 * Class containing all necessary functions for interacting with
 * the Vendor table
 */
public class BrandDAO {

    private static DatabaseConnection connection = new DatabaseConnection();

    public static List<Brand> fromResultSet(ResultSet resultSet) {
        ArrayList<Brand> brands = new ArrayList<>();
        try{
            while(resultSet.next()){
                brands.add(new Brand(resultSet.getInt(1),
                                     resultSet.getNString(2),
                                     resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    /**
     * Get brand from the db with this id
     */
    public static Brand getBrandById(int id){
        String getStatement = "SELECT * FROM brand WHERE id = " + id + ";";
        Brand brand = null;
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getStatement);
            brand = fromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand;
    }

    /**
     * Get all brands from the db
     */
    public static List<Brand> getAllBrands(){
        String s = "SELECT * FROM brand;";
        List<Brand> brands = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(s);
            brands = fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    /**
     * Updates the brand's name and designer
     */
    public static void updateBrand(Brand bran){
        String update = "UPDATE brand SET name = " + bran.getName() + ", designer = " + bran.getDesigner() +
                " WHERE brand.id = " + bran.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the brand into the Brand Table
     */
    public static void addBrand(Brand bran){
        String add = "INSERT INTO brand (name, designer) VALUES ('" + bran.getName() + "', '" + bran.getDesigner() + "');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}