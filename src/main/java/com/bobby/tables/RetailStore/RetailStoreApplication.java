package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.Customer;
import com.bobby.tables.RetailStore.repository.BrandDAO;
import com.bobby.tables.RetailStore.repository.CustomerDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Statement;
import java.sql.Date;

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

		//test, pls don't delete so i can keep testing thanks
		/*CustomerDAO b = new CustomerDAO(connection);
		Customer c = new Customer();
		c.setId(1);
		c.setFirstName("hi");
		c.setLastName("hi");
		c.setEmail("higmail.com");
		c.setPhoneNumber("5851234567");
		c.setAddress("hi");
		c.setGender("male");
		c.setDOB(new Date(System.currentTimeMillis()));
		c.setCreditCard("1234567");
		//c.isFrequentShopper(true);
		System.out.println(" yayay " + b.addCustomer(c));*/

		SpringApplication.run(RetailStoreApplication.class, args);

		connection.closeConnection();
	}
}
