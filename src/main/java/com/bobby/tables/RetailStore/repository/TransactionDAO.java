package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Transaction;

import java.sql.*;

public class TransactionDAO {

    DatabaseConnection conn;

    public TransactionDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public void addTransaction(Transaction trans){
        String add = "INSERT INTO discount (customer, store, discounts, date, quantity, product, total) VALUES ('" +
                trans.getCustomer() + "', '" + trans.getStore() + "', '" + trans.getDiscounts() + "', '" +
                trans.getDate() + "', '" + trans.getQuantityOfItem() + "', '" + trans.getProductId() + "', '" +
                trans.getTotal() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransaction(Transaction trans){
        String update = "Select * FROM transaction WHERE transaction.id = " + trans.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO: applyDiscount method
    //not sure if discount should be applied to each transaction
    //but i'm not sure how to apply it to the total
}
