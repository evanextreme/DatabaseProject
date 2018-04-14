package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model for the ShipmentProductDAO table
 */
public class ShipmentProduct {

    public interface PublicView {}

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
    @JsonView(PublicView.class)
    public Shipment getShipment() {
        return shipment;
    }
    @JsonView(PublicView.class)
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    @JsonView(PublicView.class)
    public Product getProduct() {
        return product;
    }
    @JsonView(PublicView.class)
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonView(PublicView.class)
    public int getQuantity() {
        return quantity;
    }
    @JsonView(PublicView.class)
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
