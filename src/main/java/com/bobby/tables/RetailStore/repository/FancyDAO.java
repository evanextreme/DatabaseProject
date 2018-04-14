package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class FancyDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    //top x amount of transactions per store
    public static List<Transaction> getTopTrans(int x){
        String s = "SELECT sum(quantity), product_id, store_id" +
                "FROM transaction, product" +
                "WHERE transaction.product_id = product.id" +
                "GROUP BY transaction.store_id, transaction.product_id;";
        List<Transaction> trans = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(s); //result set = table of store, product, and quantity
            int i = 0;
            while(i < x){
                rs.next();
                Product p = ProductDAO.getProductById(rs.getInt(2));
                List<TransactionProduct> tp = TransactionProductDAO.getTransactionProductsByProduct(p.getId());
                for(int j = 0; j < tp.size() && i < x; j++){
                    trans.add(tp.get(j).getTransaction());
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans;
    }

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
            //TODO: parse the result set, i'm confused about what's in the result set
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    //TODO: ???
    //TransactionProduct = (trans_id, prod_id, quantity)
    //top 3 items purchased with an item
    public static void top3Purchases(TransactionProduct tp){
        String q1 = "SELECT transactionProduct.quantity FROM transactionProduct as S, transactionProduct as T, " +
                "WHERE S.transaction_id = T.transaction_id AND T.transaction_id =" + tp.getTransaction().getId() + ";";
        //String q2 = "SELECT * FROM transactionProduct WHERE quantity = " + q1 + ";";
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(q1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
