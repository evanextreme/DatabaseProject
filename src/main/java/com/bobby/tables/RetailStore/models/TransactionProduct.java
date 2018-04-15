package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model for the TransactionProduct table
 */
public class TransactionProduct {

    public interface PublicView {}

    /** Default constructor */
    public TransactionProduct() {}

    /** Alternate constructor with fields */
    public TransactionProduct(Transaction transaction, Product product, int quantity) {
        this.transaction = transaction;
        this.product = product;
        this.quantity = quantity;
    }

    /** Alternate constructor with fields */
    public TransactionProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }


    /**
     * Prints out all info for debugging application
     * Assumes debugging record from database (so all required fields
     * like id are present)
     */
    public void debug() {
        System.out.println("Transaction Product:");
        System.out.println(">\tTransaction #: " + transaction.getId());
        System.out.println(">\tProduct #: " + product.getId());
        System.out.println(">\tQuantity: " + quantity);
    }

    // Private Fields

    private Transaction transaction;

    private Product product;

    private int quantity;

    // Getters and setters for private fields
    @JsonView(PublicView.class)
    public Transaction getTransaction() {
        return transaction;
    }
    @JsonView(PublicView.class)
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
