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
                t.setDate(new DateTime(rs.getTimestamp(3)));
                if (rs.getInt(4) != 0) {
                    t.setDiscount(DiscountDAO.getDiscountById((rs.getInt(4))));
                }
                t.setStore(StoreDAO.getStoreById(rs.getInt(5)));
                t.setTotal(rs.getDouble(6));
                if (rs.getInt(7) != 0) {
                    t.setOriginalTransaction(TransactionDAO.getTransactionById(rs.getInt(7)));
                }
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
            ResultSet resultSet = statement.executeQuery(getTransaction);
            List<Transaction> transactions = fromResultSet(resultSet);

            if (transactions.isEmpty()) {
                return null;
            }

            transaction = transactions.get(0);
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
     * Gets the latest transaction inserted
     */
    public static Transaction getNewestTransaction() {
        List<Transaction> transactions = TransactionDAO.getAllTransactions();
        Transaction latest = null;
        for (Transaction transaction : transactions) {
            if (latest == null) {
                latest = transaction;
            }

            if (latest != null && transaction.getId() > latest.getId()) {
                latest = transaction;
            }
        }
        return latest;
    }

    /**
     * Adds a transaction to the database
     */
    public static void addTransaction(Transaction trans){
        String add = "INSERT INTO transaction (customer_id, store_id, date, total";

        if (trans.getDiscount() != null && trans.getDiscount().getId() != 0) {
            add += ", discount_id";
        }

        if (trans.getOriginalTransaction() != null) {
            add += ", original_transaction_id";
        }

        add += ") VALUES (" +
                trans.getCustomer().getId() + ", " + trans.getStore().getId() + ", '" +
                trans.getDate() + "', "  + trans.getTotal();

        if (trans.getDiscount() != null) {
            add += ", " + trans.getDiscount().getId();
        }

        if (trans.getOriginalTransaction() != null) {
            add += ", " + trans.getOriginalTransaction().getId();
        }

        add += ");";

        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(add);
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
                "store_id = " + transaction.getStore().getId() + ", ";

        if (transaction.getDiscount() != null) {
            update += "discount_id = " + transaction.getDiscount().getId() + ", ";
        }

        if (transaction.getOriginalTransaction() != null) {
            update += "original_transaction_id = " + transaction.getOriginalTransaction().getId() + ", ";
        }

        update += "date = '" + transaction.getDate() + "', " +
                "total = " + transaction.getTotal() +
                " original_transaction_id = " + transaction.getOriginalTransaction().getId() +
                " WHERE id = " + transaction.getId() + ";";

        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
