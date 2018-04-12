package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Discount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {

    DatabaseConnection conn;

    public DiscountDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<Discount> getAllDiscounts(){
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

    public void addDiscount(Discount disc){
        String add = "INSERT INTO discount (percentage) VALUES ('" + disc.getPercentage() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiscount(Discount disc){
        String update = "Select * FROM discount WHERE discount.id = " + disc.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
