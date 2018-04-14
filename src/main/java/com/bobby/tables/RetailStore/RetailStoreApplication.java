package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import com.bobby.tables.RetailStore.repository.*;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@EnableWebMvc
public class RetailStoreApplication {

	public static final String LOCATION = "~/LittleBobbyTablesDb";
	public static final String USER = "LittleBobbyTables";
	public static final String PASSWORD = "csci320";


	public static DatabaseConnection connection;

	public static void main(String[] args) {

		connection = new DatabaseConnection();
		connection.createConnection(LOCATION, USER, PASSWORD);

		// Create the db schema
		connection.createDbAndTables();

		// Initializes the database with sample data
		connection.initializeDbAndTables();

		testAllDbDAOs();

		SpringApplication.run(RetailStoreApplication.class, args);
	}

	/**
	 * Get the static db connection used in whole app
	 * @return
     */
	public static DatabaseConnection getConnection() {
		if (connection == null) {
			connection = new DatabaseConnection();
			connection.createConnection(LOCATION, USER, PASSWORD);
		}

		return connection;
	}

	private static void testAllDbDAOs() {
		Customer customer = CustomerDAO.getCustomerById(1);
		Store store = StoreDAO.getStoreById(1);
		Product product1 = ProductDAO.getProductById(1);
		Product product2 = ProductDAO.getProductById(5);
		double total = product1.getCurrentPrice() + product2.getCurrentPrice() + product2.getCurrentPrice();

		Transaction transaction = new Transaction(customer, store, DateTime.now(), total);
		TransactionProduct transactionProductA = new TransactionProduct(transaction, product1, 1);
		TransactionProduct transactionProductB = new TransactionProduct(transaction, product2, 2);

		List<TransactionProduct> cart = new ArrayList<>();
		cart.add(transactionProductA);
		cart.add(transactionProductB);

		System.out.println("Before purchase...");
		product1.debug();
		product2.debug();

		CustomerDAO.purchaseItemsForCustomer(transaction, cart);

		List<Transaction> transactions = CustomerDAO.viewCustomerTransactions(customer);
		for (Transaction t : transactions) {
			t.debug();
			System.out.println("Getting associated products...");
			List<TransactionProduct> transactionProducts =
					TransactionProductDAO.getTransactionProductsByTransaction(t.getId());
			transactionProducts.forEach(TransactionProduct::debug);
		}

		System.out.println("After purchase...");
		ProductDAO.getProductById(1).debug();
		ProductDAO.getProductById(5).debug();
	}
}
