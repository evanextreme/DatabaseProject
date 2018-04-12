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

    public static DatabaseConnection connection = new DatabaseConnection();

    /**
     *  Returns a list of Stores from a given ResultSet from the db
     */
    public static List<Store> fromResultSet(ResultSet resultSet) {
        List<Store> stores = new ArrayList<>();
        try {
            while (resultSet.next()) {
                stores.add(new Store(
                        resultSet.getInt(1),
                        resultSet.getNString(2),
                        resultSet.getNString(3),
                        resultSet.getNString(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return stores;
    }

    /**
     * Gets a store from the db with the specified id
     */
    public static Store getStoreById(int id){
        String getStore = "SELECT * FROM store WHERE store.id = " + id + ";";
        Store store = new Store();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(getStore);
            store = fromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return store;
    }

    /**
     * Get all stores from the db
     * @return
     */
    public static List<Store> getAllStores(){
        String all = "SELECT * FROM store;";
        List<Store> stores = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            stores = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    /**
     * Updates the corresponding store in the db with the info in this store
     */
    public static void updateStore(Store store){
        String update = "UPDATE store SET phone_number = " + store.getPhoneNumber() +
                            ", address = " + store.getAddress() +
                            ", email = " + store.getEmail() +
                             " WHERE id = " + store.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the store to the db
     */
    public static void addStore(Store store){
        String add = "INSERT INTO store (phone_number, address, email) VALUES ('" + store.getPhoneNumber() + "', '" +
                store.getAddress() + "', '" + store.getEmail() + "');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * View all the transactions for a store
     */
    public static List<Transaction> viewStoreTrans(Store store){
        String s = "SELECT * FROM transaction WHERE store.id = " + store.getId() + ";";
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(s);
            transactions = TransactionDAO.fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * View all shipments for a store
     */
    public static List<Shipment> viewStoreShipments(Store store){
        String s = "SELECT * FROM shipment WHERE store.id = " + store.getId() + ";";
        List<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(s);
            shipments = ShipmentDAO.fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    /**
     * View all other stores and their information
     */
    public static List<Store> getOtherStores(Store store){
        String all = "SELECT * FROM store WHERE id !=" + store.getId() + ";";
        List<Store> stores = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            stores = StoreDAO.fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
