package database;

import objects.Discount;
import org.h2.tools.Csv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to create and manipulate the Discount table
 *
 * @author Theodora Bendlin
 */
public class DiscountTable {

    public static final String TABLE_NAME = "discount";
    public static final String CSV_FILE = "sample_data/discount.csv";
    public static final String SCHEMA_FILE = "db/discount.sql";

    /**
     *  Calls database creation method from DatabaseTable class
     */
    public static void createTable(Connection connection) {
        DatabaseTable.createTable(connection, SCHEMA_FILE);
    }

    /**
     * Creates an sql statement to do a bulk add discounts
     * @param discounts: list of Discount objects to add
     */
    public static String createInsertSQL(ArrayList<Discount> discounts){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO discount (ID, PERCENTAGE) VALUES");

        for(int i = 0; i < discounts.size(); i++){
            Discount discount = discounts.get(i);
            sb.append(String.format("(%d, %d)", discount.getId(), discount.getPercentage()));

            if( i != discounts.size()-1){
                sb.append(",");
            } else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Populates the Discount table with information located in
     * the discount.csv file
     */
    public static void populateTableFromCSV(Connection connection) throws SQLException {

        ArrayList<Discount> discounts = new ArrayList<>();

        try {
            ResultSet resultSet = new Csv().read(CSV_FILE, null, null);

            int numColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                int[] discountData = new int[numColumns];
                for (int index = 0; index < numColumns; index++) {
                    try {
                        discountData[index] = Integer.parseInt(resultSet.getString(index + 1));
                    } catch (NumberFormatException ex) {
                        System.err.println("Unable to parse int: " + resultSet.getString(index + 1));
                        System.exit(0);
                    }

                }
                discounts.add(new Discount(discountData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        String sql = createInsertSQL(discounts);
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    /**
     * Queries and print the table
     */
    public static void printTable(Connection connection){
        String query = "SELECT * FROM discount;";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                System.out.printf("Discount %d: %d\n",
                        result.getInt(1),
                        result.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
