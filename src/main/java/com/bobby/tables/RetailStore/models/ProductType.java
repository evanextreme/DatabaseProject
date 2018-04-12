package com.bobby.tables.RetailStore.models;

/**
 * Model class for ProductType table
 */
public class ProductType {

    /** Default constructor */
    public ProductType() {}

    /** Alternate constructor with fields */
    public ProductType(int id, String type, int parentTypeId) {
        this.id = id;
        this.type = type;
        this.parentTypeId = parentTypeId;
    }

    // Private fields

    private int id;

    private String type;

    private int parentTypeId;

    // Getter and setters for private fields


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(int parentTypeId) {
        this.parentTypeId = parentTypeId;
    }
}
