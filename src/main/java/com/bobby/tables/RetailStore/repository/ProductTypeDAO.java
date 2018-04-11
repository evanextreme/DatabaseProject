package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.ProductType;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductTypeDAO {

    DatabaseConnection conn;

    public ProductTypeDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public void updateProductType(ProductType prodType){
        String update = "Select * FROM productType WHERE productType.id = " + prodType.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProductType(ProductType prodType){
        String add = "INSERT INTO productType (type) VALUES ('" + prodType.getType() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
