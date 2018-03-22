package objects;

import java.sql.Date;

/**
 * Model for Stores
 *
 * @author Theodora Bendlin
 */
public class Transaction {

    private int id;

    private double total;

    private Date date;

    // Foreign keys

    private int customerId;

    private int storeId;

    public Transaction(int id, double total, Date date, int customerId, int storeId) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.customerId = customerId;
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getStoreId() {
        return storeId;
    }
}
