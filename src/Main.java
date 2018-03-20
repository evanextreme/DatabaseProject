import database.CustomerTable;
import database.DatabaseConnection;
import objects.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();

        //Hard drive location of the database
        String location = "~/LittleBobbyTables/database";
        String user = "littleBobbyTables";
        String password = "Password1";

        connection.createConnection(location, user, password);
        CustomerTable.printTable(connection.getConnection());

        System.out.println("\n\nPrint results of SELECT "
                + "id, first_name "
                + "FROM person "
                + "WHERE first_name = \'Kerry\' "
                + "AND last_name = \'Fryatt\'");

        /**
         * This is one way to do this, but not the only
         *
         * Create lists to make the whole thing more generic or
         * you can just construct the whole query here
         */
        ArrayList<String> columns = new ArrayList<String>();
        columns.add("id");
        columns.add("first_name");
        columns.add("last_name");

        /**
         * Conditionals
         */
        ArrayList<String> whereClauses = new ArrayList<String>();
        whereClauses.add("first_name = \'Kerry\'");
        whereClauses.add("last_name = \'Fryatt\'");

        ResultSet resultSet = CustomerTable.queryTable(connection.getConnection(), "customer", columns, whereClauses);

        if (resultSet != null) {
            try {
                int numColumns = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    for (int index = 0; index < numColumns; index++) {
                        System.out.println(resultSet.getString(index + 1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        /*try {
            CustomerTable.createTable(connection.getConnection(), "db/" + CustomerTable.TABLE_NAME + ".csv");
            CustomerTable.populateCustomerTableFromCSV(
                    connection.getConnection(),
                    "sample_data/customer.csv");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}
