package com.bobby.tables.RetailStore.repository;

import com.bobby.tables.RetailStore.RetailStoreApplication;
import com.bobby.tables.RetailStore.database.DatabaseConnection;
import com.bobby.tables.RetailStore.models.Product;
import com.bobby.tables.RetailStore.models.ShipmentProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all necessary functions for interacting with
 * the ShipmentProductDAO table
 */
public class ShipmentProductDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    /**
     * Extrapolates a list of TransactionProducts from the result set
     */
    public static List<ShipmentProduct> fromResultSet(ResultSet resultSet) {
        List<ShipmentProduct> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                products.add(new ShipmentProduct(
                        ShipmentDAO.getShipmentById(resultSet.getInt(1)),
                        ProductDAO.getProductById(resultSet.getInt(2)),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;

    }

    /**
     *  Retrieves the transaction product with the specified transactionId and productId from the database
     */
    public static ShipmentProduct getShipmentProductByIds(int shipmentId, int productId) {
        String getProduct = "SELECT * FROM shipment_product WHERE shipment_id = " + shipmentId
                + " and product_id = " + productId + ";";
        ShipmentProduct shipmentProduct = new ShipmentProduct();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getProduct);

            List<ShipmentProduct> shipmentProducts = fromResultSet(list);
            if (shipmentProducts.isEmpty()) {
                return null;
            }

            shipmentProduct = shipmentProducts.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipmentProduct;
    }

    /**
     * Retrieves all transaction products from the database
     */
    public static List<ShipmentProduct> getAllShipmentProducts() {
        String getTransaction = "SELECT * FROM shipment_product;";
        List<ShipmentProduct> products = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getTransaction);
            products = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Adds a ShipmentProduct into the db
     */
    public static void addShipmentProduct(ShipmentProduct shipmentProduct) {
        String addTransProd = "INSERT INTO shipment_product (shipment_id, product_id, quantity) " +
                "VALUES (" + shipmentProduct.getShipment().getId() +
                ", " + shipmentProduct.getProduct().getId() +
                ", " + shipmentProduct.getQuantity() + ");";
        try {
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(addTransProd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a ShipmentProduct in the db
     */
    public static void updateShipmentProduct(ShipmentProduct shipmentProduct) {
        String updateTransProd = "UPDATE shipment_product SET " +
                "shipment_id = " + shipmentProduct.getShipment().getId() +
                ", product_id = " + shipmentProduct.getProduct().getId() +
                ", quantity = " + shipmentProduct.getQuantity() +
                " WHERE shipment_id = " + shipmentProduct.getShipment().getId() +
                " and product_id = " + shipmentProduct.getProduct().getId() + ";";
        try {
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(updateTransProd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all transaction products associated with the given transaction
     */

    public static List<ShipmentProduct> getShipmentProductsByShipment(int shipmentId) {
        String getTransactions = "SELECT * FROM shipment_product WHERE shipment_id = " + shipmentId + ";";
        List<ShipmentProduct> products = new ArrayList<>();
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(getTransactions);
            products = fromResultSet(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Returns true if there is already a shipment request created for the product.
     * Otherwise return false
     */
    public static boolean isShipmentAlreadyCreated(Product product) {
        String isCreated = "SELECT * FROM shipment, shipment_product " +
                            "WHERE shipment.id = shipment_product.shipment_id " +
                            "and store_id = " + product.getStore().getId() +
                            " and product_id = " + product.getId() +
                            " and vendor_id = " + product.getVendor().getId() +
                            " and received_date is null;";
        try{
            Statement statement = connection.getConnection().createStatement();
            ResultSet list = statement.executeQuery(isCreated);
            int numberRows = 0;
            while (list.next()) {
                numberRows++;
            }
            return numberRows != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
