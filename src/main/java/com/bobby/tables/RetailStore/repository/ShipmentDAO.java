package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.*;
import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all necessary functions for interacting with
 * the Shipment table
 */
public class ShipmentDAO {

    private static DatabaseConnection connection = new DatabaseConnection();

    // Serializes a ResultSet to a List<Shipment>
    public static List<Shipment> fromResultSet(ResultSet resultSet) {

        List<Shipment> shipments = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Shipment shipment = new Shipment(
                        resultSet.getInt(1),
                        new DateTime(resultSet.getTimestamp(2)),
                        StoreDAO.getStoreById(resultSet.getInt(4)),
                        VendorDAO.getVendorById(resultSet.getInt(5)),
                        ProductDAO.getProductById(resultSet.getInt(6)),
                        resultSet.getInt(7));

                if (resultSet.getTimestamp(3) != null) {
                    shipment.setReceivedDate(new DateTime(resultSet.getTimestamp(3)));
                }

                shipments.add(shipment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return shipments;
    }

    /**
     * Gets the Shipment record from the db with this id
     */
    public static Shipment getShipmentById(int id) {
        Shipment shipment = null;
        String getStatement = "SELECT * FROM shipment WHERE id = " + id +";";
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(getStatement);
            shipment = fromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipment;
    }

    /**
     * Retrieve all Shipment records from the db
     * @return
     */
    public static List<Shipment> getAllShipments(){
        String all = "SELECT * FROM shipment;";
        ArrayList<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    /**
     * Updates the corresponding shipment record in the db
     */
    public static void updateShipment(Shipment ship){
        String update = null;
        if (ship.getReceivedDate() != null) {
            update = "UPDATE shipment SET placed_date = '" + ship.getPlacedDate().toString() +
                    "', received_date = '" + ship.getReceivedDate().toString() +
                    "', store_id = " + ship.getStore().getId() +
                    ", vendor_id = " + ship.getVendor().getId() +
                    ", product_id = " + ship.getProduct().getId() +
                    ", quantity = " + ship.getQuantityOfItem() +
                    " WHERE shipment.id = " + ship.getId() + ";";
        } else {
            update = "UPDATE shipment SET placed_date = " + ship.getPlacedDate() +
                    ", received_date = null" +
                    ", store_id = " + ship.getStore().getId() +
                    ", vendor_id = " + ship.getVendor().getId() +
                    ", product_id = " + ship.getProduct().getId() +
                    ", quantity = " + ship.getQuantityOfItem() +
                    " WHERE shipment.id = " + ship.getId() + ";";
        }
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the shipment to the db
     */
    public static void addShipment(Shipment ship){
        String add = null;
        if (ship.getReceivedDate() != null) {
            add = "INSERT INTO shipment (placed_date, received_date, quantity, product, store, vendor) VALUES ('" +
                    ship.getPlacedDate() + "', null, '" + ship.getQuantityOfItem() + "', '" +
                    ship.getProduct() + "', '" + ship.getStore() + "', '" + ship.getVendor() + "');";
        } else {
            add = "INSERT INTO shipment (placed_date, received_date, quantity, product, store, vendor) VALUES ('" +
                    ship.getPlacedDate() + "', '" + ship.getReceivedDate() + "', '" + ship.getQuantityOfItem() + "', '" +
                    ship.getProduct() + "', '" + ship.getStore() + "', '" + ship.getVendor() + "');";
        }
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
