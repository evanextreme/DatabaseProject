package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
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
 * the Vendor table
 */
public class VendorDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    public static List<Vendor> fromResultSet(ResultSet resultSet) {
        List<Vendor> vendors = new ArrayList<>();
        try {
            while (resultSet.next()) {
                vendors.add(new Vendor(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendors;
    }

    /**
     * Get Vendor with specified Id
     */
    public static Vendor getVendorById(int id) {
        Vendor vendor = null;
        String getStatement = "SELECT * FROM vendor WHERE id = " + id + ";";

        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getStatement);
            vendor = fromResultSet(resultSet).get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return vendor;
    }

    /**
     * Get all vendors listed in the database
     */
    public static List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String getStatement = "SELECT * FROM vendor;";

        try {
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getStatement);
            vendors = fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    /**
     * Updates the vendor entry in the db with the vendor
     * id to have the corresponding name and email fields
     */
    public static void updateVendor(Vendor vend){
        String update = "UPDATE vendor SET name = '" + vend.getName() + "', email = '" + vend.getEmail() +
                "' WHERE id = " + vend.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a vendor into the db
     */
    public static void addVendor(Vendor vend){
        String add = "INSERT INTO vendor (name, email) VALUES ('" + vend.getName() + "', '" + vend.getEmail() + "');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Views all shipments associated with a vendor (unordered)
     */
    public static List<Shipment> viewVendorShipments(Vendor vend){
        String s = "SELECT * FROM shipment WHERE vendor_id = " + vend.getId() + ";";
        List<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet result = state.executeQuery(s);
            shipments = ShipmentDAO.fromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    /**
     * Get all pending shipments for a vendor, ordered by the date that they were placed
     */
    public static List<Shipment> viewPendingVendorShipments(Vendor vendor) {
        String getShipments = "SELECT * FROM shipment WHERE vendor_id = " + vendor.getId() + " and" +
                " received_date is null ORDER BY placed_date";
        List<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet result = state.executeQuery(getShipments);
            shipments = ShipmentDAO.fromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }

    /**
     * Fills a shipment request for a vendor. Sets the shipment
     * as "received" by the store and increments the quantity of the
     * product in that store
     */
    public static void fillVendorShipment(Shipment shipment) {
        shipment.setReceivedDate(DateTime.now());
        ShipmentDAO.updateShipment(shipment);

        List<ShipmentProduct> products = ShipmentProductDAO.getShipmentProductsByShipment(shipment.getId());
        for (ShipmentProduct shipmentProduct : products) {
            Product product = ProductDAO.getProductById(shipmentProduct.getProduct().getId());
            product.incrementQuantity(shipmentProduct.getQuantity());
            ProductDAO.updateProduct(product);
        }
    }
}
