package com.bobby.tables.RetailStore.models;

/**
 * Model for the ShipmentProductDAO table
 */
public class ShipmentProduct {

    /** Default constructor */
    public ShipmentProduct() {}

    /** Alternate constructor with fields */
    public ShipmentProduct(Shipment transaction, Product product, int quantity) {
        this.shipment = transaction;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Prints out all info for debugging application
     * Assumes debugging record from database (so all required fields
     * like id are present)
     */
    public void debug() {
        System.out.println("Shipment Product:");
        System.out.println(">\tShipment #: " + shipment.getId());
        System.out.println(">\tProduct #: " + product.getId());
        System.out.println(">\tQuantity: " + quantity);
    }

    // Private Fields

    private Shipment shipment;

    private Product product;

    private int quantity;

    // Getters and setters for private fields

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
