package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Shipment;

import java.sql.SQLException;
import java.sql.Statement;

public class ShipmentDAO {

    DatabaseConnection conn;

    public ShipmentDAO(DatabaseConnection connection){
        this.conn = connection;
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
