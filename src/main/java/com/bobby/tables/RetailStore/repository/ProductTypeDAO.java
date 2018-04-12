package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Brand;
import com.bobby.tables.RetailStore.models.ProductType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAO {

    DatabaseConnection conn;

    public ProductTypeDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<ProductType> getAllProductType(){
        String all = "SELECT * FROM product_type;";
        ArrayList<ProductType> types = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                ProductType t = new ProductType();
                t.setId(list.getInt(1));
                t.setType(list.getNString(2));
                types.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public void updateProductType(ProductType prodType){
        String update = "Select * FROM product_type WHERE productType.id = " + prodType.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProductType(ProductType prodType){
        String add = "INSERT INTO product_type (type) VALUES ('" + prodType.getType() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
