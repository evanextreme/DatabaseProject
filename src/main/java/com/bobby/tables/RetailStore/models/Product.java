package com.bobby.tables.RetailStore.models;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Model class for Product table
 */
public class Product {

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

    // Private fields

    public int id;

    public String name;

    private double regularPrice;

    private double salePrice;

    //TODO: Enum?
    private String size;

    private int quantityInStore;

    private String department;

    private Vendor vendor;

    private Store store;

    private ProductType productType;

    private Brand brand;

    // Getter and setters for private fields
    @JsonView(Product.class)
    public int getId() {
        return id;
    }
    @JsonView(Product.class)
    public void setId(int id) {
        this.id = id;
    }

    @JsonView(Product.class)
    public String getName() {
        return name;
    }
    @JsonView(Product.class)
    public void setName(String name) {
        this.name = name;
    }

    @JsonView(Product.class)
    public double getRegularPrice() {
        return regularPrice;
    }
    @JsonView(Product.class)
    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    @JsonView(Product.class)
    public int getQuantityInStore() {
        return quantityInStore;
    }
    @JsonView(Product.class)
    public void setQuantityInStore(int quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    @JsonView(Product.class)
    public String getDepartment() {
        return department;
    }
    @JsonView(Product.class)
    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonView(Product.class)
    public String getSize() {
        return size;
    }
    @JsonView(Product.class)
    public void setSize(String size) {
        this.size = size;
    }

    @JsonView(Product.class)
    public double getSalePrice() {
        return salePrice;
    }
    @JsonView(Product.class)
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @JsonView(Product.class)
    public Vendor getVendor() {
        return vendor;
    }
    @JsonView(Product.class)
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @JsonView(Product.class)
    public Store getStore() {
        return store;
    }
    @JsonView(Product.class)
    public void setStore(Store store) {
        this.store = store;
    }

    @JsonView(Product.class)
    public Brand getBrand() {
        return brand;
    }
    @JsonView(Product.class)
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @JsonView(Product.class)
    public ProductType getProductType() {
        return productType;
    }
    @JsonView(Product.class)
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
