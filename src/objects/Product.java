package objects;

/**
 * Model for Products
 *
 * @author Theodora Bendlin
 */
public class Product {

    private int id;

    private String name;

    private double regularPrice;

    private double salesPrice;

    private String size;

    private int quantityInStore;

    private String department;

    // Foreign keys

    private int brandId;

    private int vendorId;

    private int productType;

    public Product() { /* Default empty constructor */ }

    /**
     * Constructor containing only necessary attributes
     */
    public Product(int id, String name, double regularPrice,
                   String size, int quantityInStore, String department,
                   int productType, int brandId, int vendorId) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.size = size;
        this.quantityInStore = quantityInStore;
        this.department = department;
        this.productType = productType;
        this.brandId = brandId;
        this.vendorId = vendorId;
    }

    /**
     * Full constructor
     */
    public Product(int id, String name, double regularPrice, double salesPrice,
                   String size, int quantityInStore, String department,
                   int productType, int brandId, int vendorId) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.salesPrice = salesPrice;
        this.size = size;
        this.quantityInStore = quantityInStore;
        this.department = department;
        this.productType = productType;
        this.brandId = brandId;
        this.vendorId = vendorId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public String getSize() {
        return size;
    }

    public int getQuantityInStore() {
        return quantityInStore;
    }

    public String getDepartment() {
        return department;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public int getProductType() {
        return productType;
    }
}
