package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShipmentDAO {

    DatabaseConnection conn;

    public ShipmentDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    public List<Shipment> getAllShipments(){
        String all = "SELECT * FROM shipment;";
        ArrayList<Shipment> ships = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet list = state.executeQuery(all);
            while(list.next()){
                Shipment s = new Shipment();
                s.setId(list.getInt(1));
                s.setPlacedDate((DateTime)list.getObject(2));
                s.setReceivedDate((DateTime)list.getObject(3));
                s.setQuantityOfItem(list.getInt(4));
                s.setProduct((Product)list.getObject(5));
                s.setStore((Store)list.getObject(6));
                s.setVendor((Vendor)list.getObject(7));
                ships.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ships;
    }

    public void updateShipment(Shipment ship){
        String update = "Select * FROM shipment WHERE shipment.id = " + ship.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addShipment(Shipment ship){
        String add = "INSERT INTO shipment (placed_date, recieved_date, quantity, product, store, vendor) VALUES ('" +
                ship.getPlacedDate() + "', '" + ship.getReceivedDate() + "', '" + ship.getQuantityOfItem() + "', '" +
                ship.getProduct() + "', '" + ship.getStore() + "', '" + ship.getVendor() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
