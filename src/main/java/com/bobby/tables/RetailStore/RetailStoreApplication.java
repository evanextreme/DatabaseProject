package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.Customer;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.models.Vendor;
import com.bobby.tables.RetailStore.repository.BrandDAO;
import com.bobby.tables.RetailStore.repository.CustomerDAO;
import com.bobby.tables.RetailStore.repository.VendorDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Statement;
import java.sql.Date;
import java.util.List;

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
		connection.createDbAndTables();

		// Initializes the database with sample data
		connection.initializeDbAndTables();

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
