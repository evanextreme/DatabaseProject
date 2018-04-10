package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;

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

    private int productId;

    private int storeId;

    private int vendorId;

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
