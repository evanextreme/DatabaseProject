package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Class containing all necessary functions for interacting with
 * the Customer table
 */
public class CustomerDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    /**
     * Extrapolates a list of Customers from the result set
     */
    public static List<Customer> fromResultSet(ResultSet rs) {
        List<Customer> custs = new ArrayList<>();
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt(1));
                c.setFirstName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setAddress(rs.getString(5));
                c.setDOB(rs.getDate(6));
                c.setGender(rs.getString(7));
                c.setPhoneNumber(rs.getString(8));
                c.setCreditCard(rs.getString(9));
                c.setFrequentShopper(rs.getBoolean(10));
                custs.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return custs;
    }

    /**
     *  Retrieves the customer with the specified id from the database
     */
    public static Customer getCustomerById(int id){
        String cust = "SELECT * FROM customer WHERE id = " + id + ";";
        Customer customer = new Customer();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet newCust = state.executeQuery(cust);
            customer = fromResultSet(newCust).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    /**
     * Retrieves all customers from the db
     */
    public static List<Customer> getAllCustomers(){
        String all = "SELECT * FROM customer;";
        List<Customer> custs = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            custs = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custs;
    }

    /**
     * Gets the latest customer inserted
     */
    public static Customer getNewestCustomer() {
        List<Customer> customers = CustomerDAO.getAllCustomers();
        Customer latest = null;
        for (Customer customer : customers) {
            if (latest == null) {
                latest = customer;
            }

            if (latest != null && customer.getId() > latest.getId()) {
                latest = customer;
            }
        }
        return latest;
    }

    /**
     * Adds the customer to the db
     */
    public static String addCustomer(Customer cust){
        String add = "INSERT INTO customer (first_name, last_name, email, phone_number, address, gender, dob, " +
                "credit_card, frequent_shopper) VALUES ('" + cust.getFirstName() + "', '" + cust.getLastName() +
                "', '" + cust.getEmail() + "', '" + cust.getPhoneNumber() + "', '" + cust.getAddress() + "', '" +
                cust.getGender() + "', '" + cust.getDOB() + "', '" + cust.getCreditCard() + "', '" +
                cust.isFrequentShopper() + "');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "it worked";
    }

    /**
     * Updates customer info in the db
     */
    public static void updateCustomer(Customer cust){
        String update = "UPDATE customer SET " +
                "first_name = '" + cust.getFirstName() + "', " +
                "last_name = '" + cust.getLastName() + "', " +
                "email = '" + cust.getEmail() + "', " +
                "phone_number = '" + cust.getPhoneNumber() + "', " +
                "address = '" + cust.getAddress() + "', " +
                "gender = '" + cust.getGender() + "', " +
                "credit_card = '" + cust.getCreditCard() + "', " +
                "dob = '" + cust.getDOB() + "', " +
                "frequent_shopper = " + cust.isFrequentShopper() +
                " WHERE id = " + cust.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all transactions for a customer ordered by the timestamp
     */
    public static List<Transaction> viewCustomerTransactions(Customer cust){
        String s = "SELECT * FROM transaction WHERE customer_id = " + cust.getId() + " and original_transaction_id is null ORDER BY date;";
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            transactions = TransactionDAO.fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Displays all returns for a customer ordered by the timestamp
     */
    public static List<Transaction> viewCustomerReturns(Customer customer) {
        String s = "(SELECT * FROM transaction WHERE customer_id = " + customer.getId() + " ORDER BY date) " +
                "except (SELECT * FROM transaction WHERE customer_id = " + customer.getId() + " and original_transaction_id " +
                "is null ORDER BY date);";
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            transactions = TransactionDAO.fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    /**
     * Gets all returns for a customer's transaction ordered by the timestamp
     */
    public static List<Transaction> getCustomerReturnsForTransaction(Customer customer, Transaction transaction) {
        String getTransactions = "SELECT * FROM transaction WHERE customer_id = " + customer.getId() + " and " +
                "original_transaction_id = " + transaction.getId() + " ORDER BY date;";

        List<Transaction> returns = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet list = state.executeQuery(getTransactions);
            returns = TransactionDAO.fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returns;
    }

    /**
     * Checks to see if a customer has already returned all of the transaction products for this
     * transaction product
     */
    public static boolean customerCanReturn(Transaction transaction, TransactionProduct transactionProduct) {
        List<Transaction> returns = getCustomerReturnsForTransaction(transaction.getCustomer(), transaction);
        int totalForProduct = 0;
        for (Transaction returned : returns) {
            TransactionProduct returnedProduct = TransactionProductDAO.getTransactionProductByIds(returned.getId(),
                                                transactionProduct.getProduct().getId());
            if (returnedProduct != null) {
                totalForProduct += returnedProduct.getQuantity();
            }
        }

        return totalForProduct != transactionProduct.getQuantity();
    }

    /**
     * Iterates through the customer's transactions and
     * commits the changes to the data base. Expects a transaction and
     * a list of transaction products
     */
    public static void purchaseItemsForCustomer(Transaction transaction, List<TransactionProduct> products) {

        // Add the overall transaction to the database
        TransactionDAO.addTransaction(transaction);

        // Get the new id for the transaction
        transaction = TransactionDAO.getNewestTransaction();

        // Iterate through all transaction products
        for (TransactionProduct transProduct : products) {

            // Add the TransactionProduct to the database
            transProduct.setTransaction(transaction);
            TransactionProductDAO.addTransactionProduct(transProduct);

            Product product = transProduct.getProduct();
            product.decrementQuantity(transProduct.getQuantity());
            ProductDAO.updateProduct(product);
        }
    }

    /**
     * Returns customer items. Expects a transaction reference to create the return, as well as a list
     * of transaction products to return.
     *
     * Assumes another part of the application already ensured that the customer is allowed to return
     * the items in the list
     */
    public static void returnItemsFromCustomer(Transaction transaction, List<TransactionProduct> returns) {

        double returnAmount = 0;
        for (TransactionProduct productReturn : returns) {
            returnAmount += (productReturn.getProduct().getCurrentPrice() * productReturn.getQuantity());
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setCustomer(transaction.getCustomer());
        newTransaction.setStore(transaction.getStore());
        newTransaction.setTotal(returnAmount);
        newTransaction.setDate(DateTime.now());
        newTransaction.setOriginalTransaction(transaction);
        TransactionDAO.addTransaction(newTransaction);

        newTransaction = TransactionDAO.getNewestTransaction();

        // Foreach transaction product in the returns, return it to the database and update the product count
        for (TransactionProduct productReturn : returns) {

            TransactionProductDAO.addTransactionProduct(new TransactionProduct(
                    newTransaction,
                    productReturn.getProduct(),
                    productReturn.getQuantity()
            ));

            // Update the corresponding product record
            Product product = productReturn.getProduct();
            product.incrementQuantity(productReturn.getQuantity());
            ProductDAO.updateProduct(product);
        }

    }

}
