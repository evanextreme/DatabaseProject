package objects;

import java.io.IOException;

/**
 * Model for Brands
 *
 * @author Theodora Bendlin
 */
public class Brand {

    private int id;

    private String name;

    private String designer;

    /**
     * Default constructor
     */
    public Brand(int id, String name, String designer) {
        this.id = id;
        this.name = name;
        this.designer = designer;
    }

    /**
     * Alternate constructor that initializes brand from csv data
     */
    public Brand(String[] brandData) {
        try {
            this.id = Integer.parseInt(brandData[0]);
        } catch (NumberFormatException ex) {
            System.err.printf("Could not parse id: " + brandData[0]);
            this.id = 0;
        }

        this.name = brandData[1];
        this.designer = brandData[2];
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesigner() {
        return designer;
    }
}
