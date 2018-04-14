package com.bobby.tables.RetailStore.models;

import org.joda.time.DateTime;
import org.thymeleaf.util.DateUtils;

/**
 * Model class for Shipment table
 */
public class Shipment {

    /** Default constructor */
    public Shipment() {}

    /** Alternate constructor with fields */
    public Shipment(DateTime placedDate, Store store, Vendor vendor, Product product, int quantityOfItem) {
        this.placedDate = placedDate;
        this.quantityOfItem = quantityOfItem;
        this.product = product;
        this.store = store;
        this.vendor = vendor;
    }

    /** Alternate constructor with fields */
    public Shipment(int id, DateTime placedDate, Store store, Vendor vendor, Product product, int quantityOfItem) {
        this.id = id;
        this.placedDate = placedDate;
        this.quantityOfItem = quantityOfItem;
        this.product = product;
        this.store = store;
        this.vendor = vendor;
    }

    /** Alternate constructor with fields */
    public Shipment(DateTime placedDate, DateTime receivedDate, Store store, Vendor vendor,
                        Product product, int quantityOfItem) {
        this.placedDate = placedDate;
        this.receivedDate = receivedDate;
        this.quantityOfItem = quantityOfItem;
        this.product = product;
        this.store = store;
        this.vendor = vendor;
    }

    /** Alternate constructor with fields */
    public Shipment(int id, DateTime placedDate, DateTime receivedDate,
                    Store store, Vendor vendor, Product product, int quantityOfItem) {
        this.id = id;
        this.placedDate = placedDate;
        this.receivedDate = receivedDate;
        this.quantityOfItem = quantityOfItem;
        this.product = product;
        this.store = store;
        this.vendor = vendor;
    }

    /**
     * Prints out all info for debugging application
     * Assumes debugging record from database (so all required fields
     * like id are present)
     */
    public void debug() {
        System.out.println("Shipment #" + id);
        System.out.println(">\tPlaced Date: " + placedDate.toString());
        if (receivedDate != null) {
            System.out.println(">\tReceived Date: " + receivedDate.toString());
        } else {
            System.out.println(">\tUNFILLED");
        }
        System.out.println(">\tProduct: " + product.getId());
        System.out.println(">\tStore: " + store.getId());
        System.out.println(">\tVendor: " + vendor.getId());
        System.out.println(">\tQuantity: " + quantityOfItem);
    }

    // Private fields

    private int id;

    // Timestamp for when the store placed the shipment request
    private DateTime placedDate;

    // Timestamp for when the vendor filled the shipment request
    private DateTime receivedDate;

    private int quantityOfItem;

    private Product product;

    private Store store;

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
}
