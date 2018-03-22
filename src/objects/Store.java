package objects;

/**
 * Model for Stores
 *
 * @author Theodora Bendlin
 */
public class Store {

    private int id;

    private String phoneNumber;

    private String address;

    public Store(int id, String phoneNumber, String address) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
