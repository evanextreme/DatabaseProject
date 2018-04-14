package com.bobby.tables.RetailStore.database;

import com.bobby.tables.RetailStore.models.Product;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.models.Store;
import com.bobby.tables.RetailStore.models.Vendor;
import com.bobby.tables.RetailStore.repository.ShipmentDAO;
import com.bobby.tables.RetailStore.repository.ShipmentProductDAO;
import org.h2.api.Trigger;
import org.joda.time.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Trigger class that will automatically create shipment requests for a store
 * when the inventory for a particular item is low
 */
public class AutomaticShipmentTrigger implements Trigger {

    @Override
    public void init(Connection conn, String schemaName,
                     String triggerName, String tableName, boolean before, int type)
            throws SQLException {}

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow)
            throws SQLException {

        Product product = new Product();
        Store store = new Store();
        Vendor vendor = new Vendor();

        product.setId((int) newRow[0]);

        store.setId((int) newRow[5]);
        product.setStore(store);

        vendor.setId((int) newRow[3]);
        product.setVendor(vendor);

        if ((int) newRow[9] < 5 && !ShipmentProductDAO.isShipmentAlreadyCreated(product)) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO shipment (store_id, vendor_id, placed_date) " +
                            "VALUES (?, ?, ?);" +
                    "INSERT INTO shipment_product (shipment_")
            ) {
                stmt.setInt(1, (int) newRow[5]);
                stmt.setInt(2, (int) newRow[3]);
                stmt.setInt(3, (int) newRow[0]);
                stmt.setTimestamp(4, new Timestamp(DateTime.now().getMillis()));
                stmt.setInt(5, 20);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void close() throws SQLException {}

    @Override
    public void remove() throws SQLException {}
}
