package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;
import java.util.Date;

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
        String s = "SELECT * FROM transaction WHERE id = " + cust.getId() + " ORDER BY date;";
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
     * Iterates through the customer's transactions and
     * commits the changes to the data base. Expects a list of
     * Transaction items with all fields already initialized
     */
    public static void purchaseItemsForCustomer(List<Transaction> cart) {
        for (Transaction item : cart) {
            TransactionDAO.addTransaction(item);
            String updateProducts = "UPDATE product SET quantity = quantity - " + item.getQuantityOfItem() +
                   " WHERE store_id = " + item.getStore().getId() + " and id = " + item.getProduct().getId()
                    + ";";
            try {
                Statement statement = connection.getConnection().createStatement();
                statement.executeUpdate(updateProducts);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * Returns customer items. Expects a map of transaction_id, quantityToReturn
     * pairs
     */
    public static void returnItemsFromCustomer(Map<Integer, Integer> returnReceipt) {
        for (Integer key : returnReceipt.keySet()) {
            Transaction transaction = TransactionDAO.getTransactionById(key);
            if (transaction == null) {
                //TODO: Handle this better?
                return;
            }

            String updateTransaction = "UPDATE transaction SET quantity = quantity - " + returnReceipt.get(key) +
                    " WHERE id = " + key + ";";

            String updateProduct = "UPDATE product SET quantity = quantity + " + returnReceipt.get(key)
                    + " WHERE id = " + transaction.getProduct().getId()
                    + " and store_id = " + transaction.getStore().getId();

            try {
                Statement statement = connection.getConnection().createStatement();
                statement.executeUpdate(updateTransaction);
                statement.executeUpdate(updateProduct);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
