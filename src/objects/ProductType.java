package objects;

/**
 * Model for ProductTypes
 *
 * @author Theodora Bendlin
 */
public class ProductType {

    private int id;

    private String type;

    private int parentTypeId;

    /**
     * Default constructor
     */
    public ProductType (int id, String type, int parentTypeId) {
        this.id = id;
        this.type = type;
        this.parentTypeId = parentTypeId;
    }

    /**
     * Alternate constructor that initializes vendor from csv data
     */
    public ProductType(String[] productTypeData) {
        try {
            this.id = Integer.parseInt(productTypeData[0]);
            this.parentTypeId = Integer.parseInt(productTypeData[2]);
        } catch (NumberFormatException ex) { /* null, no op */ }

        this.type = productTypeData[1];
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getParentTypeId() {
        return parentTypeId;
    }
}
