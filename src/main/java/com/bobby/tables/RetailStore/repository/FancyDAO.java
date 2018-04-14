package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.models.Store;
import com.bobby.tables.RetailStore.models.Transaction;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class FancyDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    //top transactions per store
    //maybe take an int so it doesn't have to be top 20, can be top 5 or whatever
    public static List<Transaction> getTop20Trans(){
        String s = "SELECT sum(quantity), product_id, store_id" +
                "FROM transaction, product" +
                "WHERE transaction.product_id = product.id" +
                "GROUP BY transaction.store_id, transaction.product_id;";
        List<Transaction> trans = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s);
            //trans = TransactionList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans; //empty
    }

    //need TransactionProduct to implement this
    /*public static List<Transaction> TransactionList(ResultSet rs){
        List<Transaction> trans = new ArrayList<>();
        try{
            int i = 0;
            while(i < 20){
                rs.next();
                Transaction t = new Transaction();
                Store s = StoreDAO.getStoreById(rs.getInt(1));
                rs.getInt(2);
                rs.getInt(3);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }*/


    //list of stores in descending order of sales
    public static List<Store> getSalesPerStore(){
        String s = "SELECT sum(total), store_id FROM transaction GROUP BY transaction.store_id;";
        List<Store> stores = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s);
            while(rs.next()){
                stores.add(StoreDAO.getStoreById(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    //create shipment request to vendor
    public static void createShipmentRequest(Shipment ship){
        String s = "INSERT INTO shipment (quantity, product_id, store_id, vendor_id) VALUES (" + ship.getQuantityOfItem() + ", '" +
                ship.getProduct().getId() + "', '" + ship.getStore().getId() + "', '" + ship.getVendor().getId() + "');";
        try{
            Statement statement = connection.getConnection().createStatement();
            statement.execute(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //vendor fills shipment
    public static void fillShipment(Shipment ship, DateTime placed){
        ship.setPlacedDate(placed);
        ShipmentDAO.updateShipment(ship);
    }

    //x dept outsells y dept in ____ stores
    public static int DeptOutsell(String dept1, String dept2){
        String s = "SELECT COUNT(store) FROM(SELECT * FROM transaction, store, product WHERE " +
                "transaction.product_id = product.id AND transaction.store_id = store.id;";
        int x = 0;
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s);
            //what do i do with the result set?
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    //also need TransactionProduct to do this
    //TransactionProduct = (trans_id, prod_id, quantity)
    //top 3 items purchased with an item

    /*public static void topPurchases(TransactionProduct tp){
        String q1 = "SELECT tp.quantity FROM transactionProduct as S, transactionProduct as T, " +
                "WHERE S.transaction_id = T.transaction_id AND T.transaction_id = tp.transaction_id;";
        String q2 = "SELECT * FROM transactionProduct WHERE quantity = " + q1 + ";";
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet first = statement.executeQuery(q1); //should i execute and get int before running q2?
            ResultSet ans = statement.executeQuery(q2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;

    }*/

}
