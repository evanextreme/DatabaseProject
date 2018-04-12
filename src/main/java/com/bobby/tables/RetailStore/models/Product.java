package com.bobby.tables.RetailStore.models;

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

    // Private fields

    private int id;

    private String name;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getQuantityInStore() {
        return quantityInStore;
    }

    public void setQuantityInStore(int quantityInStore) {
        this.quantityInStore = quantityInStore;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
