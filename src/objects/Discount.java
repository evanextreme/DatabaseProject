package objects;

/**
 * Model for Discounts
 *
 * @author Theodora Bendlin
 */
public class Discount {

    private int id;

    private int percentage;

    /**
     *  Default constructor
     */
    public Discount(int id, int percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    /**
     *  Alternate constructor that initializes discount with
     *  int array
     */
    public Discount(int[] discountData) {
        this.id = discountData[0];
        this.percentage = discountData[1];
    }

    public int getId() {
        return id;
    }

    public int getPercentage() {
        return percentage;
    }

    public double reducedAmount(double price) {
        return price - (price * (percentage / 100));
    }
}
