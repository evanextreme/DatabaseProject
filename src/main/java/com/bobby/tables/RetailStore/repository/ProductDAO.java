package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    DatabaseConnection conn;

    public ProductDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public void addProduct(Product prod){
        String add = "INSERT INTO product (name, regular_price, sales_price, size, quantity, department, vendor) " +
                "VALUES ('" + prod.getName() + "', '" + prod.getRegularPrice() + "', '" + prod.getSalePrice() + "', '"
                + prod.getSize() + "', '" + prod.getQuantityInStore() + "', '" + prod.getDepartment() + "', '" +
                prod.getVendor() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product prod){
        String update = "Select * FROM product WHERE product.id = " + prod.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> viewProductTrans(Product prod){
        String s = "SELECT * FROM transaction WHERE product.id = " + prod.getId() + ";";
        ArrayList<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                Transaction t = new Transaction();
                t.setId(list.getInt(1));
                t.setCustomer((Customer) list.getObject(2));
                t.setStore((Store) list.getObject(3));
                if(list.getObject(4) != null)
                    t.setDiscounts((List<Discount>) list.getObject(4));
                t.setDate((DateTime) list.getObject(5));
                t.setQuantityOfItem(list.getInt(6));
                t.setTotal(list.getDouble(7));
                t.setProductId(list.getInt(8));
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;

    }

    public ArrayList<String> viewProductShipments(Product prod){
        String s = "SELECT * FROM shipment WHERE product.id = " + prod.getId() + ";";
        ArrayList<String> shipments = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                shipments.add(list.getNString(1)); //adding id of shipment, should rest of cols be added?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }
}
