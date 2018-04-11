package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Discount;

import java.sql.*;

public class DiscountDAO {

    DatabaseConnection conn;

    public DiscountDAO(DatabaseConnection connection){
        this.conn = connection;
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
