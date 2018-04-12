package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class CustomerDAO {

    DatabaseConnection conn;

    public CustomerDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<Customer> getAllCustomers(){
        String all = "SELECT * FROM customer;";
        ArrayList<Customer> custs = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Customer c = new Customer();
                c.setId(list.getInt(1));
                c.setFirstName(list.getNString(2));
                c.setLastName(list.getNString(3));
                c.setEmail(list.getNString(4));
                c.setPhoneNumber(list.getNString(5));
                c.setAddress(list.getNString(6));
                c.setGender(list.getNString(7));
                c.setDOB((Date)list.getObject(8));
                c.setCreditCard(list.getNString(9));
                c.setFrequentShopper(list.getBoolean(10));
                custs.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custs;
    }

    public String addCustomer(Customer cust){
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

    public void updateCustomer(Customer cust){
        String update = "Select * FROM customer WHERE customer.id = " + cust.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //reciept
    public ArrayList<Transaction> viewCustomerTrans(Customer cust){
        String s = "SELECT * FROM transaction WHERE customer.id = " + cust.getId() + ";";
        ArrayList<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                Transaction t = new Transaction();
                t.setId(list.getInt(1));
                t.setCustomer((Customer) list.getObject(2));
                t.setStore((Store) list.getObject(3));
                if(list.getObject(4) != null)
                    t.setDiscount((Discount) list.getObject(4));
                t.setDate((DateTime) list.getObject(5));
                t.setQuantityOfItem(list.getInt(6));
                t.setTotal(list.getDouble(7));
                t.setProduct((Product) list.getObject(8));
                transactions.add(t);
            }
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
    public void purchaseItemsForCustomer(List<Transaction> cart) {
        TransactionDAO transactionDAO = new TransactionDAO(conn);
        for (Transaction item : cart) {
            transactionDAO.addTransaction(item);

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
    public void returnItemsFromCustomer(Map<Integer, Integer> returnReceipt) {
        TransactionDAO transactionDAO = new TransactionDAO(conn);
        for (Integer key : returnReceipt.keySet()) {
            Transaction transaction = transactionDAO.getTransactionWithId(key);
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
