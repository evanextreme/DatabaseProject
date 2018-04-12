package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Product;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.models.Store;
import com.bobby.tables.RetailStore.models.ProductType;
import com.bobby.tables.RetailStore.models.Vendor;
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

    DatabaseConnection conn;

    /**
     * Default constructor. Needs to pass in the database connection
     */
    public VendorDAO(DatabaseConnection connection){
        this.conn = connection;
    }

    /**
     * Get all vendors listed in the database
     */
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        String getStatement = "SELECT * FROM vendor;";

        try {
            Statement statement = conn.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(getStatement);

            while (resultSet.next()) {
                Vendor vendor = new Vendor();
                vendor.setId(resultSet.getInt(1));
                vendor.setName(resultSet.getString(2));
                vendor.setEmail(resultSet.getString(3));
                vendors.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendors;
    }

    /**
     * Updates the vendor entry in the db with the vendor
     * id to have the corresponding name and email fields
     */
    public void updateVendor(Vendor vend){
        String update = "UPDATE vendor SET name = " + vend.getId() + ", email = " + vend.getEmail() +
                " WHERE id = " + vend.getId() + ";";
        try{
            Statement state = conn.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a vendor into the db
     */
    public void addVendor(Vendor vend){
        String add = "INSERT INTO vendor (name, email) VALUES ('" + vend.getName() + "', '" + vend.getEmail() + "');";
        try{
            Statement state = conn.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Views all shipments associated with a vendor (unordered)
     */
    public List<Shipment> viewVendorShipments(Vendor vend){
        String s = "SELECT * FROM shipment WHERE vendor_id = " + vend.getId() + ";";
        List<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = conn.getConnection().createStatement();
            ResultSet result = state.executeQuery(s);
            shipments = Shipment.fromResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }
}
