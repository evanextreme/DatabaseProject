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

    public static DatabaseConnection conn;

    public StoreDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public static List<Store> fromResultSet(ResultSet rs) {
        List<Store> stores = new ArrayList<>();
        try {
            while (rs.next()) {
                Store s = new Store();
                s.setId(rs.getInt(1));
                s.setPhoneNumber(rs.getNString(2));
                s.setAddress(rs.getNString(3));
                s.setEmail(rs.getNString(4));
                stores.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stores;
    }

    public static Store getStoreById(int id){
        String store = "SELECT * FROM store WHERE store.id = " + id + ";";
        Store s = new Store();
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(store);
            ResultSet newStore = state.executeQuery(store);
            s = fromResultSet(newStore).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static List<Store> getAllStores(){
        String all = "SELECT * FROM store;";
        List<Store> stores = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            stores = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    public static void updateStore(Store store){
        String update = "Select * FROM store WHERE store.id = " + store.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStore(Store store){
        String add = "INSERT INTO store (phone_number, address, email) VALUES ('" + store.getPhoneNumber() + "', '" +
                store.getAddress() + "', '" + store.getEmail() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Transaction> viewStoreTrans(Store store){
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
                    t.setDiscount((Discount) list.getObject(4));
                t.setDate((DateTime) list.getObject(5));
                t.setQuantityOfItem(list.getInt(6));
                t.setTotal(list.getDouble(7));
                t.setProduct((Product) list.getObject(8));
                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static ArrayList<Shipment> viewStoreShipments(Store store){
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

    public static List<Store> getOtherStores(Store store){
        String all = "SELECT * FROM store WHERE id !=" + store.getId() + ";";
        ArrayList<Store> stores = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Store s = new Store();
                s.setId(list.getInt(1));
                s.setPhoneNumber(list.getNString(2));
                s.setAddress(list.getNString(3));
                s.setEmail(list.getNString(4));
                stores.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
