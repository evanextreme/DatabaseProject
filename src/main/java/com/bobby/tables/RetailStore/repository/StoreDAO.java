package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

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
        String update = "UPDATE store SET phone_number = '" + store.getPhoneNumber() +
                            "', address = '" + store.getAddress() +
                            "', email = '" + store.getEmail() +
                             "' WHERE id = " + store.getId() + ";";
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
            state.executeUpdate(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * View all the transactions for a store
     */
    public static List<Transaction> viewStoreTransactions(Store store){
        String s = "SELECT * FROM transaction WHERE id = " + store.getId() + ";";
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
        String s = "SELECT * FROM shipment WHERE id = " + store.getId() + ";";
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
     * Creates a shipment request from the given store to the given vendor with the
     * list of products
     */
    public static void createShipmentRequest(Store store, Vendor vendor, List<ShipmentProduct> products) {
        ShipmentDAO.addShipment(new Shipment(DateTime.now(), store, vendor));
        Shipment newShipment = ShipmentDAO.getNewestShipment();

        for (ShipmentProduct product : products) {
            product.setShipment(newShipment);
            ShipmentProductDAO.addShipmentProduct(product);
        }
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

    /**
     * The following are more complex queries that stores are able to run to look at some
     * data about their operation
     */

    /**
     * Returns the top x number of transactions ordered by store
     */
    public static List<Transaction> getTopTransactions(int number){
        List<Store> stores = StoreDAO.getAllStores();
        List<Transaction> transactions = new ArrayList<>();
        for (Store store : stores) {
            String query = "SELECT TOP " + number + " id, store_id, total " +
                    "FROM transaction " +
                    "WHERE store_id = " + store.getId() +
                    "ORDER BY total DESC";
            try {
                Statement statement = connection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    transactions.add(TransactionDAO.getTransactionById(resultSet.getInt(1)));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return transactions;
    }

    /**
     * Returns the list of stores ordered by their sales
     */
    public static List<Store> getStoresBySales() {
        String query = "SELECT sum(total), store_id FROM transaction GROUP BY store_id ORDER BY sum(total) DESC";
        List<Store> stores = new ArrayList<>();
        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                stores.add(StoreDAO.getStoreById(resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
