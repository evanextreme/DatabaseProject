package objects;

/**
 * Model for Vendors
 *
 * @author Theodora Bendlin
 */
public class Vendor {

    private int id;

    private String name;

    /**
     * Default Constructor
     */
    public Vendor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Alternate constructor that initializes vendor from csv data
     */
    public Vendor(String[] vendorData) {
        try {
            this.id = Integer.parseInt(vendorData[0]);
        } catch (NumberFormatException ex) {
            System.err.printf("Could not parse id: " + vendorData[0]);
            this.id = 0;
        }

        this.name = vendorData[1];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
