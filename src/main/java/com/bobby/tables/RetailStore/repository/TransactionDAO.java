package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;

public class TransactionDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    // Serializes a ResultSet to a List<Transaction>
    public static List<Transaction> fromResultSet (ResultSet rs) {
        List<Transaction> trans = new ArrayList<>();
        try {
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt(1));
                t.setCustomer(CustomerDAO.getCustomerById(rs.getInt(2)));
                t.setStore(StoreDAO.getStoreById(rs.getInt(3)));
                if (rs.getInt(4) != 0) {
                    t.setDiscount(DiscountDAO.getDiscountById((rs.getInt(4))));
                }
                t.setDate(new DateTime(rs.getTimestamp(5)));
                t.setQuantityOfItem(rs.getInt(6));
                t.setProduct(ProductDAO.getProductById(rs.getInt(7)));
                t.setTotal(rs.getInt(8));
                trans.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trans;
    }

    /**
     * Retrieves the transaction with the specified id from the database
     */
    public static Transaction getTransactionById(int id){
        String getTransaction = "SELECT * FROM transaction WHERE transaction.id = " + id + ";";
        Transaction transaction = new Transaction();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getTransaction);
            transaction = fromResultSet(list).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }


    /**
     * Gets all transactions from the db
     */
    public static List<Transaction> getAllTransactions(){
        String all = "SELECT * FROM transaction;";
        List<Transaction> trans = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            trans = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans;
    }

    /**
     * Adds a transaction to the database
     */
    public static void addTransaction(Transaction trans){
        String add = "INSERT INTO transaction (customer_id, store_id, discount_id, date, quantity, product_id, total) VALUES ('" +
                trans.getCustomer().getId() + "', '" + trans.getStore().getId() + "', '" + trans.getDiscount().getId() + "', '" +
                trans.getDate() + "', '" + trans.getQuantityOfItem() + "', '" + trans.getProduct().getId() + "', '" +
                trans.getTotal() + "');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the transaction db record with the same transaction id
     */
    public static void updateTransaction(Transaction transaction) {
        String update = "UPDATE transaction SET " +
                "customer_id = " + transaction.getCustomer().getId() + ", " +
                "store_id = " + transaction.getStore().getId() + ", " +
                "discount_id = " + transaction.getDiscount().getId() + ", " +
                "date = '" + transaction.getDate() + "', " +
                "quantity = " + transaction.getQuantityOfItem() + ", " +
                "product_id = " + transaction.getProduct().getId() + ", " +
                "total = " + transaction.getTotal() + ", " +
                " WHERE id = " + transaction.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: applyDiscount method
    //not sure if discount should be applied to each transaction
    //but i'm not sure how to apply it to the total
}
