package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Model class for Shipment table
 */
public class Shipment {

    // Private fields

    private int id;

    // Timestamp for when the store placed the shipment request
    private DateTime placedDate;

    // Timestamp for when the vendor filled the shipment request
    private DateTime receivedDate;

    private int quantityOfItem;

    // Foreign key reference for db
    private int productId;

    // Navigation property for application
    private Product product;

    // Foreign key reference for db
    private int storeId;

    // Navigation property for application
    private Store store;

    // Foreign key reference for db
    private int vendorId;

    // Navigation property for application
    private Vendor vendor;

    // Getter and setters for private fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(DateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public DateTime getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(DateTime placedDate) {
        this.placedDate = placedDate;
    }

    public int getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(int quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
