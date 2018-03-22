package database;

import objects.Vendor;
import org.h2.tools.Csv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to create and manipulate the brand table
 *
 * @author Theodora Bendlin
 */
public class VendorTable {

    public static final String TABLE_NAME = "vendor";
    public static final String CSV_FILE = "sample_data/vendor.csv";
    public static final String SCHEMA_FILE = "db/vendor.sql";

    /**
     *  Calls database creation method from DatabaseTable class
     */
    public static void createTable(Connection connection) {
        DatabaseTable.createTable(connection, SCHEMA_FILE);
    }

    /**
     * Creates an sql statement to do a bulk add vendors
     * @param vendors: list of Vendor objects to add
     */
    public static String createInsertSQL(ArrayList<Vendor> vendors){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO vendor (ID, NAME) VALUES");

        for(int i = 0; i < vendors.size(); i++){
            Vendor vendor = vendors.get(i);
            sb.append(String.format("(%d, \'%s\')", vendor.getId(), vendor.getName()));

            if( i != vendors.size()-1){
                sb.append(",");
            } else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Populates the Vendor table with information located in
     * the vendor.csv file
     */
    public static void populateTableFromCSV(Connection connection) throws SQLException {

        ArrayList<Vendor> vendors = new ArrayList<>();

        try {
            ResultSet resultSet = new Csv().read(CSV_FILE, null, null);

            int numColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] vendorData = new String[numColumns];
                for (int index = 0; index < numColumns; index++) {
                    vendorData[index] = resultSet.getString(index + 1);
                }
                vendors.add(new Vendor(vendorData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        String sql = createInsertSQL(vendors);
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    /**
     * Queries and print the table
     */
    public static void printTable(Connection connection){
        String query = "SELECT * FROM vendor;";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                System.out.printf("Vendor %d: %s\n",
                        result.getInt(1),
                        result.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
