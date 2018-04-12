package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.ProductType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all necessary functions for interacting with
 * the ProductType table
 */
public class ProductTypeDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    /**
     * Returns a list of ProductTypes from a result set
     */
    public static List<ProductType> fromResultSet(ResultSet resultSet) {
        List<ProductType> types = new ArrayList<>();
        try{
            while(resultSet.next()){
                types.add(new ProductType(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    /**
     * Get ProductType from the db with this id
     */
    public static ProductType getProductTypeById(int id) {
        String all = "SELECT * FROM product_type WHERE id = " + id + ";";
        ProductType type = new ProductType();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            type = fromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * Get all ProductTypes from the db
     */
    public static List<ProductType> getAllProductTypes(){
        String all = "SELECT * FROM product_type;";
        List<ProductType> types = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            types = fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    /**
     * Updates the ProductType
     */
    public static void updateProductType(ProductType prodType){
        String update = "UPDATE product_type SET name = '" + prodType.getType() +
                        "', parent_id = " + prodType.getParentTypeId() +
                        " WHERE id = " + prodType.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a ProductType to the db
     */
    public static void addProductType(ProductType prodType){
        String add = "INSERT INTO product_type (name, parent_id) VALUES ('" + prodType.getType() +
                                            "'," + prodType.getParentTypeId() + ");";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
