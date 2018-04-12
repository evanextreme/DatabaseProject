package com.bobby.tables.RetailStore.repository;

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

    public static DatabaseConnection conn;

    /**
     * Default constructor. Needs to pass in the database connection
     */
    public DiscountDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public static List<Discount> fromResultSet(ResultSet rs) {
        List<Discount> disc = new ArrayList<>();
        try {
            while (rs.next()) {
                Discount d = new Discount();
                d.setId(rs.getInt(1));
                d.setPercentage(rs.getInt(2));
                disc.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return disc;
    }

    public static Discount getDiscountById(int id){
        String store = "SELECT * FROM discount WHERE discount.id = " + id + ";";
        Discount d = new Discount();
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(store);
            ResultSet newDisc = state.executeQuery(store);
            d = fromResultSet(newDisc).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static List<Discount> getAllDiscounts(){
        String all = "SELECT * FROM discount;";
        ArrayList<Discount> discounts = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Discount d = new Discount();
                d.setId(list.getInt(1));
                d.setPercentage(list.getInt(2));
                discounts.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    /**
     * Inserts a discount into the discount table
     * @param disc
     */
    public static void addDiscount(Discount disc){
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
    public static void updateDiscount(Discount disc){
        String update = "UPDATE discount SET percentage = " + disc.getPercentage() + " WHERE discount.id = " + disc.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
