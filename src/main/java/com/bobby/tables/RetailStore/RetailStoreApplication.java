package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import com.bobby.tables.RetailStore.repository.*;
import org.h2.api.Trigger;
import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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

		// Create the triggers for the db
		connection.createDbTriggers();

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
		Vendor vendor = product2.getVendor();
		double total = product1.getCurrentPrice() + product2.getCurrentPrice() + product2.getCurrentPrice();

		Transaction transaction = new Transaction();
		transaction.setCustomer(customer);
		transaction.setStore(store);
		transaction.setDate(DateTime.now());
		transaction.setTotal(total);
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

		List<Shipment> shipments = ShipmentDAO.getAllShipments();
		Shipment shipment = shipments.get(shipments.size() - 1);
		shipment.debug();

		System.out.println("\n\nRETURNING AN ITEM\n\n");;

		transactions = CustomerDAO.viewCustomerTransactions(customer);
		transaction = TransactionDAO.getTransactionById(transactions.size());
		List<Product> associatedProducts = new ArrayList<>();

		if (transaction == null) {
			return;
		}

		transaction.debug();

		List<TransactionProduct> productsToReturn = TransactionProductDAO.getTransactionProductsByTransaction(transaction.getId());
		for (TransactionProduct tp : productsToReturn) {
			System.out.println("Returning...");
			tp.setQuantity(1);
			tp.debug();
			associatedProducts.add(tp.getProduct());
			System.out.println("Allowed to return? " + CustomerDAO.customerCanReturn(transaction, tp));
		}

		CustomerDAO.returnItemsFromCustomer(transaction, productsToReturn);

		System.out.println("\n\nAFTER RETURNING");

		transaction = TransactionDAO.getTransactionById(transaction.getId());
		if (transaction == null) {
			return;
		}

		List<Transaction> returns = CustomerDAO.viewCustomerReturns(customer);
		for (Transaction returned : returns) {
			List<TransactionProduct> returnedProducts = TransactionProductDAO.getTransactionProductsByTransaction(returned.getId());
			returnedProducts.forEach(TransactionProduct::debug);
			System.out.println("Associated products:");
			for (Product product : associatedProducts) {
				product = ProductDAO.getProductById(product.getId());
				product.debug();
			}
		}

		shipments = ShipmentDAO.getAllShipments();
		for (Shipment ship : shipments) {
			ship.debug();
			List<ShipmentProduct> products = ShipmentProductDAO.getShipmentProductsByShipment(ship.getId());
			products.forEach(ShipmentProduct::debug);
		}

		shipments = VendorDAO.viewPendingVendorShipments(vendor);
		VendorDAO.fillVendorShipment(shipments.get(0));
		shipment = ShipmentDAO.getShipmentById(shipments.get(0).getId());
		shipment.debug();
		List<ShipmentProduct> products = ShipmentProductDAO.getShipmentProductsByShipment(shipment.getId());
		for (ShipmentProduct sp : products) {
			sp.debug();
			System.out.println("Associated Products:");
			Product product = ProductDAO.getProductById(sp.getProduct().getId());
			product.debug();
		}

	}
}
