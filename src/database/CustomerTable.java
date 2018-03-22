package database;

import objects.Customer;
import org.h2.tools.Csv;
import utils.DateUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to create and manipulate the customer table
 *
 * @author Theodora Bendlin
 */
public class CustomerTable {

    public static final String TABLE_NAME = "customer";
    public static final String CSV_FILE = "sample_data/customer.csv";
    public static final String SCHEMA_FILE = "db/customer.sql";

    /**
     *  Calls database creation method from DatabaseTable class
     */
    public static void createTable(Connection connection) {
        DatabaseTable.createTable(connection, SCHEMA_FILE);
    }

    /**
     * Creates an sql statement to do a bulk add customers
     * @param customers: list of Customer objects to add
     */
    public static String createInsertSQL(ArrayList<Customer> customers){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO customer (ID, CREDIT_CARD, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, DOB," +
                                    " GENDER, PHONE_NUMBER, FREQUENT_SHOPPER) VALUES");

        for(int i = 0; i < customers.size(); i++){
            Customer customer = customers.get(i);
            sb.append(String.format("(%d, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', \'%s\', "
                            + "\'%s\', \'%s\', \'%s\')",
                    customer.getId(), customer.getCreditCard(), customer.getFirstName(), customer.getLastName(),
                    customer.getEmail(), customer.getAddress(), DateUtils.convertDateToString(customer.getDateOfBirth()),
                    customer.getGender(), customer.getPhoneNumber(), customer.isFrequentShopper()));

            if( i != customers.size()-1){
                sb.append(",");
            } else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    public static void populateTableFromCSV(Connection connection) throws SQLException{

        ArrayList<Customer> customers = new ArrayList<>();

        try {
            ResultSet resultSet = new Csv().read(CSV_FILE, null, null);

            int numColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] customerData = new String[numColumns];
                for (int index = 0; index < numColumns; index++) {
                    customerData[index] = resultSet.getString(index + 1);
                }
                customers.add(new Customer(customerData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        String sql = createInsertSQL(customers);
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    /**
     * Queries and print the table
     */
    public static void printTable(Connection connection){
        String query = "SELECT * FROM customer;";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                System.out.printf("Customer %d: %s\n",
                        result.getInt(1),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
