package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Statement;

@SpringBootApplication
//@EnableWebMvc
public class RetailStoreApplication {

	public static void main(String[] args) {
		DatabaseConnection connection = new DatabaseConnection();

		//Hard drive location of the database
		String location = "~/LittleBobbyTablesDb";
		String user = "LittleBobbyTables";
		String password = "csci320";

		connection.createConnection(location, user, password);

		// Create the db schema
		try {
			String createStatement = "RUNSCRIPT FROM \'db\\DDL.sql\'";
			Statement statement = connection.getConnection().createStatement();
			statement.execute(createStatement);
		} catch (Exception ex) {
			System.err.println("Error: Could not run db\\DDL.sql");
			ex.printStackTrace();
			System.exit(0);
		}

		// Initializes the database with sample data

		String filenamePrefix = "db\\DML\\";
		String[] DMLFilenames = new String[] {"brand.sql", "discount.sql", "product_type.sql", "customer.sql", "store.sql",
												"vendor.sql", "product.sql", "shipment.sql", "transaction.sql",};
		try {
			for (int index = 0; index < DMLFilenames.length; index++) {
				String createStatement = "RUNSCRIPT FROM \'" + filenamePrefix + DMLFilenames[index] + "\'";
				Statement statement = connection.getConnection().createStatement();
				statement.execute(createStatement);
			}
		} catch (Exception ex) {
			System.err.println("Error: Could not run DML script");
			ex.printStackTrace();
			System.exit(0);
		}

		SpringApplication.run(RetailStoreApplication.class, args);
	}
}
