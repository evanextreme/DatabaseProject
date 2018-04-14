package com.bobby.tables.RetailStore.models;

/**
 * Model for the TransactionProduct table
 */
public class TransactionProduct {

    /** Default constructor */
    public TransactionProduct() {}

    /** Alternate constructor with fields */
    public TransactionProduct(Transaction transaction, Product product, int quantity) {
        this.transaction = transaction;
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

    public void incrementQuantity(int increment) {
        this.quantity += increment;
    }

    public void decrementQuantity(int decrement) {
        this.quantity -= decrement;
    }

    // Getters and setters for private fields

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
