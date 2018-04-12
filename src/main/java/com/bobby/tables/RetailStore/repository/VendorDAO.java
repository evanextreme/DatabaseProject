package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.ProductType;
import com.bobby.tables.RetailStore.models.Vendor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendorDAO {

    DatabaseConnection conn;

    public VendorDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<Vendor> getAllVendors(){
        String all = "SELECT * FROM vendor;";
        ArrayList<Vendor> vends = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Vendor v = new Vendor();
                v.setId(list.getInt(1));
                v.setName(list.getNString(2));
                v.setEmail(list.getNString(3));
                vends.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vends;
    }

    public void updateVendor(Vendor vend){
        String update = "Select * FROM vendor WHERE vendor.id = " + vend.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addVendor(Vendor vend){
        String add = "INSERT INTO vendor (name, email) VALUES ('" + vend.getName() + "', '" + vend.getEmail() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> viewVendorShipments(Vendor vend){
        String s = "SELECT * FROM shipment WHERE vendor.id = " + vend.getId() + ";";
        ArrayList<String> shipments = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(s);
            while(list.next()){
                shipments.add(list.getNString(1)); //adding id of shipment, should rest of cols be added?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }
}
