package com.bobby.tables.RetailStore.database;

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

        if ((int) newRow[9] < 5) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO shipment (store_id, vendor_id, product_id, placed_date, quantity) " +
                            "VALUES (?, ?, ?, ?, ?)")
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
