import database.*;
import objects.ProductType;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();

        //Hard drive location of the database
        String location = "~/LittleBobbyTables/database";
        String user = "littleBobbyTables";
        String password = "Password1";

        connection.createConnection(location, user, password);

        try {
            CustomerTable.createTable(connection.getConnection());
            CustomerTable.populateTableFromCSV(connection.getConnection());

            BrandTable.createTable(connection.getConnection());
            BrandTable.populateTableFromCSV(connection.getConnection());

            VendorTable.createTable(connection.getConnection());
            VendorTable.populateTableFromCSV(connection.getConnection());

            ProductTypeTable.createTable(connection.getConnection());
            ProductTypeTable.populateTableFromCSV(connection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
