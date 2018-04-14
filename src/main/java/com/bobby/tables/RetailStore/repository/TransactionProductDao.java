package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.TransactionProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all necessary functions for interacting with
 * the TransactionProductDAO table
 */
public class TransactionProductDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    /**
     * Extrapolates a list of TransactionProducts from the result set
     */
    public static List<TransactionProduct> fromResultSet(ResultSet resultSet) {
        List<TransactionProduct> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                products.add(new TransactionProduct(
                   TransactionDAO.getTransactionById(resultSet.getInt(1)),
                   ProductDAO.getProductById(resultSet.getInt(2)),
                   resultSet.getInt(3)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;

    }

    /**
     *  Retrieves the transaction product with the specified transactionId and productId from the database
     */
    public static TransactionProduct getTransactionProductByIds(int transactionId, int productId) {
        String getTransaction = "SELECT * FROM transaction_product WHERE transaction_id = " + transactionId
                                    + " and product_id = " + productId + ";";
        TransactionProduct transaction = new TransactionProduct();
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
     * Retrieves all transaction products from the database
     */
    public static List<TransactionProduct> getAllTransactionProducts() {
        String getTransaction = "SELECT * FROM transaction_product;";
        List<TransactionProduct> products = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getTransaction);
            products = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Adds a transactionProduct into the db
     */
    public static void addTransactionProduct(TransactionProduct transactionProduct) {
        String addTransProd = "INSERT INTO transaction_product (transaction_id, product_id, quantity) " +
                "VALUES (" + transactionProduct.getTransaction().getId() +
                ", " + transactionProduct.getProduct().getId() +
                ", " + transactionProduct.getQuantity() + ");";
        try {
            Statement state = connection.getConnection().createStatement();
            state.execute(addTransProd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a transactionProduct into the db
     */
    public static void updateTransactionProduct(TransactionProduct transactionProduct) {
        String updateTransProd = "UPDATE transaction_product SET " +
                "transaction_id = " + transactionProduct.getTransaction().getId() +
                ", product_id = " + transactionProduct.getProduct().getId() +
                ", quantity = " + transactionProduct.getQuantity() +
                " WHERE transaction_id = " + transactionProduct.getTransaction().getId() +
                        " and product_id = " + transactionProduct.getProduct().getId() + ";";
        try {
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(updateTransProd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all transaction products associated with the given transaction
     */

    public static List<TransactionProduct> getTransactionProductsByTransaction(int transactionId) {
        String getTransactions = "SELECT * FROM transaction_product WHERE transaction_id = " + transactionId + ";";
        List<TransactionProduct> transactions = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getTransactions);
            transactions = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
