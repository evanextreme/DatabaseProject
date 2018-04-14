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
 * the Product table
 */
public class ProductDAO {

    private static DatabaseConnection connection = RetailStoreApplication.getConnection();

    /**
     *  Returns a list of Products from a given ResultSet from the db
     */
    public static List<Product> fromResultSet(ResultSet resultSet) {
        List<Product> products = new ArrayList<>();
        try{
            while(resultSet.next()){
                products.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        BrandDAO.getBrandById(resultSet.getInt(3)),
                        VendorDAO.getVendorById(resultSet.getInt(4)),
                        ProductTypeDAO.getProductTypeById(resultSet.getInt(5)),
                        StoreDAO.getStoreById(resultSet.getInt(6)),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getString(9),
                        resultSet.getInt(10),
                        resultSet.getString(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Gets product with specific id
     */
    public static Product getProductById(int id) {
        String getProduct = "SELECT * FROM product WHERE id = " + id + ";";
        Product product = null;
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(getProduct);
            product = fromResultSet(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Get all products from the db
     */
    public static List<Product> getAllProducts(){
        String all = "SELECT * FROM product;";
        List<Product> products = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(all);
            products = fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Add a product to the db
     */
    public static void addProduct(Product prod){
        String add = "INSERT INTO product (name, regular_price, sale_price, size, quantity, department, store_id, " +
                "brand_id, vendor_id, product_type_id) " +
                "VALUES ('" + prod.getName() +
                        "', '" + prod.getRegularPrice() +
                        "', '" + prod.getSalePrice() +
                        "', '" + prod.getSize() +
                        "', '" + prod.getQuantityInStore() +
                        "', '" + prod.getDepartment() +
                        "', '" + prod.getStore().getId() +
                        "', '" + prod.getBrand().getId() +
                        "', '" + prod.getVendor().getId() +
                        "', '" + prod.getProductType().getId() +"');";
        try{
            Statement state = connection.getConnection().createStatement();
            state.execute(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the corresponding product record in the db
     */
    public static void updateProduct(Product prod){
        String update = "UPDATE product SET name =  '" + prod.getName() +
                "', regular_price = " + prod.getRegularPrice() +
                ", sale_price = " + prod.getSalePrice() +
                ", size = '" + prod.getSize() +
                "', quantity = " + prod.getQuantityInStore() +
                ", department = '" + prod.getDepartment() +
                "', store_id = " + prod.getStore().getId() +
                ", brand_id = " + prod.getBrand().getId() +
                ", vendor_id = " + prod.getVendor().getId() +
                ", product_type_id = " + prod.getProductType().getId() +
                 " WHERE product.id = " + prod.getId() + ";";
        try{
            Statement state = connection.getConnection().createStatement();
            state.executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * View all transactions for a product
     */
    public static List<Transaction> viewProductTrans(Product prod){
        String s = "SELECT * FROM transaction WHERE id = " + prod.getId() + ";";
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(s);
            transactions = TransactionDAO.fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;

    }

    /**
     * View all shipments for a product
     */
    public static List<Shipment> viewProductShipments(Product prod){
        String s = "SELECT * FROM shipment WHERE id = " + prod.getId() + ";";
        List<Shipment> shipments = new ArrayList<>();
        try{
            Statement state = connection.getConnection().createStatement();
            ResultSet resultSet = state.executeQuery(s);
            shipments = ShipmentDAO.fromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shipments;
    }
}
