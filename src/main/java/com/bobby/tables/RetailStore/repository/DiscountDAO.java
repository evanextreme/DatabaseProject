package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Discount;

import java.sql.*;

/**
 * Class containing all necessary functions for interacting with
 * the Discount table
 */
public class DiscountDAO {

    DatabaseConnection conn;

    /**
     * Default constructor. Needs to pass in the database connection
     */
    public DiscountDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    /**
     * Inserts a discount into the discount table
     * @param disc
     */
    public void addDiscount(Discount disc){
        String add = "INSERT INTO discount (percentage) VALUES (" + disc.getPercentage() + ");";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the discount entry in the db with the discount
     * id to have the corresponding percentage fields
     */
    public void updateDiscount(Discount disc){
        String update = "UPDATE discount SET percentage = " + disc.getPercentage() + " WHERE discount.id = " + disc.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
