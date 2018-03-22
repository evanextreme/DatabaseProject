package database;

import objects.Brand;
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
public class BrandTable {

    public static final String TABLE_NAME = "brand";
    public static final String CSV_FILE = "sample_data/brand.csv";
    public static final String SCHEMA_FILE = "db/brand.sql";

    /**
     *  Calls database creation method from DatabaseTable class
     */
    public static void createTable(Connection connection) {
        DatabaseTable.createTable(connection, SCHEMA_FILE);
    }

    /**
     * Creates an sql statement to do a bulk add brands
     * @param brands: list of Brand objects to add
     */
    public static String createInsertSQL(ArrayList<Brand> brands){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO brand (ID, NAME, DESIGNER) VALUES");

        for(int i = 0; i < brands.size(); i++){
            Brand brand = brands.get(i);
            sb.append(String.format("(%d, \'%s\', \'%s\')", brand.getId(), brand.getName(), brand.getDesigner()));

            if( i != brands.size()-1){
                sb.append(",");
            } else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Populates brand table with data from brand.csv
     */
    public static void populateTableFromCSV(Connection connection) throws SQLException {

        ArrayList<Brand> brands = new ArrayList<>();

        try {
            ResultSet resultSet = new Csv().read(CSV_FILE, null, null);

            int numColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] brandData = new String[numColumns];
                for (int index = 0; index < numColumns; index++) {
                    brandData[index] = resultSet.getString(index + 1);
                }
                brands.add(new Brand(brandData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        String sql = createInsertSQL(brands);
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    /**
     * Queries and print the table
     */
    public static void printTable(Connection connection){
        String query = "SELECT * FROM brand;";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                System.out.printf("Brand %d: %s, Designed by: %s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
