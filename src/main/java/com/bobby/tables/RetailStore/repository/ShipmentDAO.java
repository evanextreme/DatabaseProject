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

    public static DatabaseConnection conn;

    public ShipmentDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    // Serializes a ResultSet to a List<Shipment>
    public static List<Shipment> fromResultSet(ResultSet resultSet) {
        List<Shipment> shipments = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Shipment shipment = new Shipment();
                shipment.setId(resultSet.getInt(1));
                shipment.setPlacedDate(new DateTime(resultSet.getTimestamp(2)));

                if (resultSet.getTimestamp(3) != null) {
                    shipment.setReceivedDate(new DateTime(resultSet.getTimestamp(3)));
                }

                shipment.setStoreId(resultSet.getInt(4));
                shipment.setVendorId(resultSet.getInt(5));
                shipment.setProductId(resultSet.getInt(6));
                shipment.setQuantityOfItem(resultSet.getInt(7));

                shipments.add(shipment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shipments;
    }

    public static List<Shipment> getAllShipments(){
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

    public static void updateShipment(Shipment ship){
        String update = "Select * FROM shipment WHERE shipment.id = " + ship.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addShipment(Shipment ship){
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
