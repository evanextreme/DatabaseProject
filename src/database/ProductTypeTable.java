package database;

import objects.ProductType;
import org.h2.tools.Csv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to create and manipulate the Product Type table
 *
 * @author Theodora Bendlin
 */
public class ProductTypeTable {

    public static final String TABLE_NAME = "product_type";
    public static final String CSV_FILE = "sample_data/product_type.csv";
    public static final String SCHEMA_FILE = "db/product_type.sql";

    /**
     *  Calls database creation method from DatabaseTable class
     */
    public static void createTable(Connection connection) {
        DatabaseTable.createTable(connection, SCHEMA_FILE);
    }

    /**
     * Creates an sql statement to do a bulk add vendors
     * @param pTypes: list of Vendor objects to add
     */
    public static String createInsertSQL(ArrayList<ProductType> pTypes){
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO product_type (ID, NAME, PARENT_TYPE_ID) VALUES");

        for(int i = 0; i < pTypes.size(); i++){
            ProductType pType = pTypes.get(i);
            sb.append(String.format("(%d, \'%s\', %d)", pType.getId(), pType.getType(), pType.getParentTypeId()));

            if( i != pTypes.size()-1){
                sb.append(",");
            } else{
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Populates the ProductType table with information located in
     * the product_type.csv file
     */
    public static void populateTableFromCSV(Connection connection) throws SQLException {

        ArrayList<ProductType> pTypes = new ArrayList<>();

        try {
            ResultSet resultSet = new Csv().read(CSV_FILE, null, null);

            int numColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                String[] pTypeData = new String[numColumns];
                for (int index = 0; index < numColumns; index++) {
                    pTypeData[index] = resultSet.getString(index + 1);
                }
                pTypes.add(new ProductType(pTypeData));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }

        String sql = createInsertSQL(pTypes);
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    /**
     * Queries and print the table
     */
    public static void printTable(Connection connection){
        String query = "SELECT * FROM product_type;";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                System.out.printf("Product Type %d: %s, Parent Type: %d\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
