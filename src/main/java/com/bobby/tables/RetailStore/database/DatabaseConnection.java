package com.bobby.tables.RetailStore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            //TODO: You should handle this better
            e.printStackTrace();
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
