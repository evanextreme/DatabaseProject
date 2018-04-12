package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CustomerDAO {

    public static DatabaseConnection conn;

    public CustomerDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public static List<Customer> fromResultSet(ResultSet rs) throws SQLException {
        List<Customer> custs = new ArrayList<>();
        try {
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt(1));
                c.setFirstName(rs.getNString(2));
                c.setLastName(rs.getNString(3));
                c.setEmail(rs.getNString(4));
                c.setPhoneNumber(rs.getNString(5));
                c.setAddress(rs.getNString(6));
                c.setGender(rs.getNString(7));
                c.setDOB(rs.getDate(8));
                c.setCreditCard(rs.getNString(9));
                c.setFrequentShopper(rs.getBoolean(10));
                custs.add(c);
            }
        }
        catch (SQLException ex) {
        ex.printStackTrace();
        }
        return custs;
    }

    public static Customer getCustById(int id){
        String cust = "SELECT * FROM customer WHERE customer.id = " + id + ";";
        Customer c = new Customer();
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(cust);
            ResultSet newCust = state.executeQuery(cust);
            c = fromResultSet(newCust).get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static List<Customer> getAllCustomers(){
        String all = "SELECT * FROM customer;";
        List<Customer> custs = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            custs = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custs;
    }

    public static String addCustomer(Customer cust){
        String add = "INSERT INTO customer (first_name, last_name, email, phone_number, address, gender, dob, " +
                "credit_card, frequent_shopper) VALUES ('" + cust.getFirstName() + "', '" + cust.getLastName() +
                "', '" + cust.getEmail() + "', '" + cust.getPhoneNumber() + "', '" + cust.getAddress() + "', '" +
                cust.getGender() + "', '" + cust.getDOB() + "', '" + cust.getCreditCard() + "', '" +
                cust.isFrequentShopper() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "it worked";
    }

    public static void updateCustomer(Customer cust){
        String update = "Select * FROM customer WHERE customer.id = " + cust.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //receipt
    public static List<Transaction> viewCustomerTrans(Customer cust){
        String s = "SELECT * FROM transaction WHERE customer.id = " + cust.getId() + ";";
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
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
        TransactionDAO transactionDAO = new TransactionDAO(conn);
        for (Transaction item : cart) {
            TransactionDAO.addTransaction(item);

            String updateProducts = "UPDATE product SET quantity = quantity - " + item.getQuantityOfItem() +
                   " WHERE store_id = " + item.getStore().getId() + " and product_id = " + item.getProduct().getId()
                    + ";";

            try {
                Statement statement = conn.getConnection().createStatement();
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
        TransactionDAO transactionDAO = new TransactionDAO(conn);
        for (Integer key : returnReceipt.keySet()) {
            Transaction transaction = transactionDAO.getTransactionById(key);
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
                Statement statement = conn.getConnection().createStatement();
                statement.executeUpdate(updateTransaction);
                statement.executeUpdate(updateProduct);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
