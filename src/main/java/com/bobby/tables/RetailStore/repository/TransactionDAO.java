package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class TransactionDAO {

    DatabaseConnection conn;

    public TransactionDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<Transaction> getAllTransactions(){
        String all = "SELECT * FROM transaction;";
        ArrayList<Transaction> trans = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Transaction t = new Transaction();
                t.setId(list.getInt(1));
                t.setCustomer((Customer)list.getObject(2));
                t.setStore((Store)list.getObject(3));
                t.setDiscount((Discount)list.getObject(4));
                t.setDate((DateTime)list.getObject(5));
                t.setQuantityOfItem(list.getInt(6));
                t.setProductId(list.getInt(7));
                t.setTotal(list.getInt(8));
                trans.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans;
    }

    public void addTransaction(Transaction trans){
        String add = "INSERT INTO transaction (customer, store, discount, date, quantity, product, total) VALUES ('" +
                trans.getCustomer() + "', '" + trans.getStore() + "', '" + trans.getDiscount().getId() + "', '" +
                trans.getDate() + "', '" + trans.getQuantityOfItem() + "', '" + trans.getProduct().getId() + "', '" +
                trans.getTotal() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransactionWithId(int id){
        String getTransaction = "SELECT * FROM transaction WHERE transaction.id = " + id + ";";
        Transaction transaction = new Transaction();
        try{
            Statement statement = conn.getConnection().createStatement();
            statement.execute(getTransaction);
            ResultSet list = statement.executeQuery(getTransaction);

            while(list.next()){
                transaction.setId(list.getInt(1));
                transaction.setCustomer((Customer) list.getObject(2));
                transaction.setStore((Store) list.getObject(3));
                if(list.getObject(4) != null)
                    transaction.setDiscount((Discount) list.getObject(4));
                transaction.setDate((DateTime) list.getObject(5));
                transaction.setQuantityOfItem(list.getInt(6));
                transaction.setTotal(list.getDouble(7));
                transaction.setProduct((Product) list.getObject(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transaction;
    }

    //TODO: applyDiscount method
    //not sure if discount should be applied to each transaction
    //but i'm not sure how to apply it to the total
}
