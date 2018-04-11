package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    DatabaseConnection conn;

    public StoreDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public void updateStore(Store store){
        String update = "Select * FROM store WHERE store.id = " + store.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStore(Store store){
        String add = "INSERT INTO store (phone_number, address, email) VALUES ('" + store.getPhoneNumber() + "', '" +
                store.getAddress() + "', '" + store.getEmail() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> viewStoreTrans(Store store){
        String s = "SELECT * FROM transaction WHERE store.id = " + store.getId() + ";";
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

    public ArrayList<Shipment> viewStoreShipments(Store store){
        String s = "SELECT * FROM shipment WHERE store.id = " + store.getId() + ";";
        ArrayList<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                Shipment ship = new Shipment();
                ship.setId(list.getInt(1));
                ship.setPlacedDate((DateTime) list.getObject(2));
                ship.setReceivedDate((DateTime) list.getObject(3));
                ship.setQuantityOfItem(list.getInt(4));
                ship.setProduct((Product) list.getObject(5));
                ship.setStore((Store) list.getObject(6));
                ship.setVendor((Vendor) list.getObject(7));
                shipments.add(ship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }
}
