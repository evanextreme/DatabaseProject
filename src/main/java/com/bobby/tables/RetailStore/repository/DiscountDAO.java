package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Discount;
import com.bobby.tables.RetailStore.models.Store;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all necessary functions for interacting with
 * the Discount table
 */
public class DiscountDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    public static List<Discount> fromResultSet(ResultSet rs) {
        List<Discount> discounts = new ArrayList<>();
        try {
            while (rs.next()) {
                discounts.add(new Discount(rs.getInt(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return discounts;
    }

    /**
     * Get a discount from the db with this id
     */
    public static Discount getDiscountById(int id){
        String store = "SELECT * FROM discount WHERE id = " + id + ";";
        Discount d = new Discount();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet newDisc = state.executeQuery(store);
            d = fromResultSet(newDisc).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * Retrieves all discounts from the db
     */
    public static List<Discount> getAllDiscounts(){
        String all = "SELECT * FROM discount;";
        List<Discount> discounts = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            discounts = fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    /**
     * Inserts a discount into the discount table
     */
    public static void addDiscount(Discount disc){
        String add = "INSERT INTO discount (percentage) VALUES (" + disc.getPercentage() + ");";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the discount entry in the db with the discount
     * id to have the corresponding percentage fields
     */
    public static void updateDiscount(Discount disc){
        String update = "UPDATE discount SET percentage = " + disc.getPercentage() + " WHERE id = " + disc.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDiscount(Discount discount) {
        String delete = "DELETE FROM discount WHERE id = " + discount.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
