package com.bobby.tables.RetailStore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles connecting to a database
 *
 * @author Theodora Bendlin
 */
public class DatabaseConnection {

    //The connection to the database
    private Connection connection;

    /**
     * Create a database connection with the given params
     * @param location: path of where to place the database
     * @param user: user name for the owner of the database
     * @param password: password of the database owner
     */
    public void createConnection(String location, String user, String password){
        try {

            String url = "jdbc:h2:" + location;
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the actual database from the schema defined in
     * db\DDL.sql
     */
    public void createDbAndTables() {
        try {
            String createStatement = "RUNSCRIPT FROM \'db\\DDL.sql\'";
            Statement statement = connection.createStatement();
            statement.execute(createStatement);
        } catch (Exception ex) {
            System.err.println("Error: Could not run db\\DDL.sql. Exiting...");
            ex.printStackTrace();
            closeConnection();
            System.exit(0);
        }
    }

    /**
     * Initializes the database with the sample data script for each table
     * as defined in db\DML
     */
    public void initializeDbAndTables() {
        String filenamePrefix = "db\\DML\\";
        String[] DMLFilenames = new String[] {"brand.sql", "discount.sql", "product_type.sql", "customer.sql", "store.sql",
                "vendor.sql", "product.sql", "transaction.sql", "transaction_product.sql", "shipment.sql", "shipment_product.sql"};
        try {
            for (int index = 0; index < DMLFilenames.length; index++) {
                String createStatement = "RUNSCRIPT FROM \'" + filenamePrefix + DMLFilenames[index] + "\'";
                Statement statement = connection.createStatement();
                statement.execute(createStatement);
            }
        } catch (Exception ex) {
            System.err.println("Error: Could not run DML script");
            ex.printStackTrace();
            closeConnection();
            System.exit(0);
        }
    }

    public void createDbTriggers() {
        String shipmentTrigger = "CREATE TRIGGER createShipmentTrigger AFTER UPDATE ON product FOR EACH ROW " +
                "CALL \"com.bobby.tables.RetailStore.database.AutomaticShipmentTrigger\";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(shipmentTrigger);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *  Getter for connection
     */
    public Connection getConnection(){
        return connection;
    }

    /**
     * Closes connection when program ends
     */
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
