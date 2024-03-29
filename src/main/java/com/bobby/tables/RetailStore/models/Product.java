package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model class for Product table
 */
public class Product {
	
	public interface PublicView {}

    /** Default constructor */
    public Product() {}

    /** Alternate constructor with fields */
    public Product(int id, String name, Brand brand, Vendor vendor, ProductType productType, Store store,
                   double regularPrice, double salePrice, String size, int quantityInStore, String department) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.size = size;
        this.quantityInStore = quantityInStore;
        this.department = department;
        this.vendor = vendor;
        this.store = store;
        this.brand = brand;
        this.productType = productType;
    }

    /** Alternate constructor with fields */
    public Product(String name, Brand brand, Vendor vendor, ProductType productType, Store store,
                   double regularPrice, double salePrice, String size, int quantityInStore, String department) {
        this.name = name;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.size = size;
        this.quantityInStore = quantityInStore;
        this.department = department;
        this.vendor = vendor;
        this.store = store;
        this.brand = brand;
        this.productType = productType;
    }

    /**
     * Prints out all info for debugging application
     * Assumes debugging record from database (so all required fields
     * like id are present)
     */
    public void debug() {
        System.out.println("Product #" + id);
        System.out.println(">\tName: " + name);
        System.out.println(">\tSize: " + size);
        System.out.println(">\tRegular Price: $" + regularPrice);
        if (regularPrice != salePrice) {
            System.out.println(">\tSale Price: $" + salePrice);
        }
        System.out.println(">\tDepartment: " + department);
        System.out.println(">\tStore: " + store.getId());
        System.out.println(">\tVendor: " + vendor.getName());
        System.out.println(">\tBrand: " + brand.getId());
        System.out.println(">\tQuantity in Store: " + quantityInStore);
    }

    /**
     * Gets the sale price if product is on sale, otherwise gets the
     * regular price of the product
     */
    public double getCurrentPrice() {
        if (salePrice == 0 || salePrice == regularPrice) {
            return regularPrice;
        }

        return salePrice;
    }

    public void incrementQuantity(int increment) {
        this.quantityInStore += increment;
    }

    public void decrementQuantity(int decrement) {
        this.quantityInStore -= decrement;
    }

    // Private fields

    private int id;

    private String name;

    private double regularPrice;

    private double salePrice;

    private String size;

    private int quantityInStore;

    private String department;

    private Vendor vendor;

    private Store store;

    private ProductType productType;

    private Brand brand;

    // Getter and setters for private fields
    @JsonView(PublicView.class)
    public int getId() {
        return id;
    }
    @JsonView(PublicView.class)
    public void setId(int id) {
        this.id = id;
    }

    @JsonView(PublicView.class)
    public String getName() {
        return name;
    }
    @JsonView(PublicView.class)
    public void setName(String name) {
        this.name = name;
    }

    @JsonView(PublicView.class)
    public double getRegularPrice() {
        return regularPrice;
    }
    @JsonView(PublicView.class)
    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    @JsonView(PublicView.class)
    public int getQuantityInStore() {
        return quantityInStore;
    }
    @JsonView(PublicView.class)
    public void setQuantityInStore(int quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    @JsonView(PublicView.class)
    public String getDepartment() {
        return department;
    }
    @JsonView(PublicView.class)
    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonView(PublicView.class)
    public String getSize() {
        return size;
    }
    @JsonView(PublicView.class)
    public void setSize(String size) {
        this.size = size;
    }

    @JsonView(PublicView.class)
    public double getSalePrice() {
        return salePrice;
    }
    @JsonView(PublicView.class)
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @JsonView(PublicView.class)
    public Vendor getVendor() {
        return vendor;
    }
    @JsonView(PublicView.class)
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @JsonView(PublicView.class)
    public Store getStore() {
        return store;
    }
    @JsonView(PublicView.class)
    public void setStore(Store store) {
        this.store = store;
    }

    @JsonView(PublicView.class)
    public Brand getBrand() {
        return brand;
    }
    @JsonView(PublicView.class)
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @JsonView(PublicView.class)
    public ProductType getProductType() {
        return productType;
    }
    @JsonView(PublicView.class)
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
