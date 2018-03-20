package objects;


import utils.DateUtils;

import java.util.Date;

/**
 *  Class that holds data about a customer
 *
 *  @author Theodora Bendlin
 */
public class Customer {

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";

    public Customer (int id, String creditCard) {
        this.id = id;
        this.creditCard = creditCard;
    }

    public Customer(String[] customerData) {
        this.id = Integer.parseInt(customerData[0]);
        this.creditCard = customerData[1];
        this.firstName = customerData[2];
        this.lastName = customerData[3];
        this.email = customerData[4];
        this.address = customerData[5];
        this.gender = customerData[6];
        this.dateOfBirth = DateUtils.parseDateFromString(customerData[7]);
        this.phoneNumber = customerData[8];
        this.isFrequentShopper = customerData[9] == null || customerData[9].toUpperCase().equals("FALSE");
    }

    // Fields for anonymous & frequent shoppers

    private int id;

    private String creditCard;

    // Fields for frequent shopper program

    private boolean isFrequentShopper;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private Date dateOfBirth;

    private String gender;

    private String phoneNumber;

    public int getId() {
        return id;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String isFrequentShopper() {
        return (isFrequentShopper ? "TRUE" : "FALSE");
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}
