package com.bobby.tables.RetailStore;

import com.bobby.tables.RetailStore.database.DatabaseConnection;

import com.bobby.tables.RetailStore.models.*;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.models.Vendor;
import com.bobby.tables.RetailStore.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.util.DateUtils;

import java.sql.Connection;
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
		// Testing BRAND

		/*System.out.println("EXECUTING TESTS FOR BRAND");

		System.out.println("Inserting a new brand...");
		BrandDAO.addBrand(new Brand("My Brand", "Theodora Bendlin"));

		System.out.println("Getting all brands...");
		List<Brand> allBrands = BrandDAO.getAllBrands();
		for (Brand brand : allBrands) {
			System.out.printf("%d: name = %s, designer = %s\n", brand.getId(), brand.getName(), brand.getDesigner());
		}

		System.out.println("Getting brand with id = 5...");
		Brand brand = BrandDAO.getBrandById(5);
		System.out.printf("%d: name = %s, designer = %s\n", brand.getId(), brand.getName(), brand.getDesigner());

		System.out.println("Updating My Brand...");
		BrandDAO.updateBrand(new Brand(11, "My Brand", "Tia Bendlin"));

		System.out.println("Getting brand with id = 11...");
		brand = BrandDAO.getBrandById(11);
		System.out.printf("%d: name = %s, designer = %s\n", brand.getId(), brand.getName(), brand.getDesigner());*/

		// Testing VENDOR

		/*System.out.println("EXECUTING TESTS FOR VENDOR");

		System.out.println("Inserting a new vendor...");
		VendorDAO.addVendor(new Vendor("My Vendor", "vendor@vendor.com"));

		System.out.println("Getting all brands...");
		List<Vendor> allVendors = VendorDAO.getAllVendors();
		for (Vendor vendor : allVendors) {
			System.out.printf("%d: name = %s, email = %s\n", vendor.getId(), vendor.getName(), vendor.getEmail());
		}

		System.out.println("Getting vendor with id = 5...");
		Vendor vendor = VendorDAO.getVendorById(5);
		System.out.printf("%d: name = %s, email = %s\n", vendor.getId(), vendor.getName(), vendor.getEmail());

		System.out.println("Updating My Vendor...");
		VendorDAO.updateVendor(new Vendor(11, "My Vendor", "timox223@vendor.com"));

		System.out.println("Getting vendor with id = 11...");
		vendor = VendorDAO.getVendorById(11);
		System.out.printf("%d: name = %s, email = %s\n", vendor.getId(), vendor.getName(), vendor.getEmail());

		System.out.println("Viewing vendor shipments for id = 5");
		vendor = VendorDAO.getVendorById(2);
		List<Shipment> shipments = VendorDAO.viewVendorShipments(vendor);
		for (Shipment shipment : shipments) {
			System.out.printf("%d: Placed = %s, Received? = %b, Store = %d, Vendor = %d, Product = %d, Quantity = %d\n",
					shipment.getId(), shipment.getPlacedDate().toString(), shipment.getReceivedDate() != null, shipment.getStore().getId(),
					shipment.getVendor().getId(), shipment.getProduct().getId(), shipment.getQuantityOfItem() );
		}

		System.out.println("Viewing Pending vendor shipments for id = 5");
		shipments = VendorDAO.viewPendingVendorShipments(vendor);
		for (Shipment shipment : shipments) {
			System.out.printf("%d: Placed = %s, Received? = %b, Store = %d, Vendor = %d, Product = %d, Quantity = %d\n",
					shipment.getId(), shipment.getPlacedDate().toString(), shipment.getReceivedDate() != null, shipment.getStore().getId(),
					shipment.getVendor().getId(), shipment.getProduct().getId(), shipment.getQuantityOfItem() );
		}*/

		// Testing DISCOUNT

		/*System.out.println("EXECUTING TESTS FOR DISCOUNT");

		System.out.println("Inserting a new discount...");
		DiscountDAO.addDiscount(new Discount(70));

		System.out.println("Getting all discounts...");
		List<Discount> allDiscounts = DiscountDAO.getAllDiscounts();
		for (Discount discount : allDiscounts) {
			System.out.printf("%d: percent off = %d\n", discount.getId(), discount.getPercentage());
		}

		System.out.println("Getting discount with id = 5...");
		Discount discount = DiscountDAO.getDiscountById(5);
		System.out.printf("%d: percent off = %d\n", discount.getId(), discount.getPercentage());

		System.out.println("Updating My Discount...");
		DiscountDAO.updateDiscount(new Discount(11, 75));

		System.out.println("Getting discount with id = 11...");
		discount = DiscountDAO.getDiscountById(11);
		System.out.printf("%d: percent off = %d\n", discount.getId(), discount.getPercentage());*/

		// Testing STORE

		System.out.println("EXECUTING TESTS FOR STORE");

		System.out.println("Inserting a new store...");
		StoreDAO.addStore(new Store("8455545656", "26 No Where Avenue Otisville Ny", "email@email.com"));

		System.out.println("Getting all Stores...");
		List<Store> allStores = StoreDAO.getAllStores();
		for (Store store : allStores) {
			System.out.printf("%d: email = %s, phone = %s, address = %s\n", store.getId(), store.getEmail(),
					store.getPhoneNumber(), store.getAddress());
		}

		System.out.println("Getting Store with id = 5...");
		Store store = StoreDAO.getStoreById(5);
		System.out.printf("%d: email = %s, phone = %s, address = %s\n", store.getId(), store.getEmail(),
				store.getPhoneNumber(), store.getAddress());

		System.out.println("Updating My Store...");
		StoreDAO.updateStore(new Store(allStores.size(), "8455545656", "76 Hometown St. NY", "email@email.com"));

		System.out.println("Getting Store with id = 11...");
		store = StoreDAO.getStoreById(allStores.size());
		System.out.printf("%d: email = %s, phone = %s, address = %s\n", store.getId(), store.getEmail(),
				store.getPhoneNumber(), store.getAddress());

		System.out.println("Getting all transactions for store with id = 2");
		store = StoreDAO.getStoreById(2);
		/*List<Transaction> transactions = StoreDAO.viewStoreTransactions(store);
		for (Transaction transaction : transactions) {
			System.out.printf("%d:\n Total = %.2f, Product = %d, Store = %d, Customer = %d, Quantity = %d\n",
									transaction.getId(), transaction.getTotal(), transaction.getProduct().getId(),
									transaction.getStore().getId(), transaction.getCustomer().getId(),
									transaction.getQuantityOfItem());
		}*/

		System.out.println("Getting all shipments for store with id = 2");
		List<Shipment> shipments = StoreDAO.viewStoreShipments(store);
		for (Shipment shipment : shipments) {
			System.out.printf("%d: Placed = %s, Received? = %b, Store = %d, Vendor = %d, Product = %d, Quantity = %d\n",
					shipment.getId(), shipment.getPlacedDate().toString(), shipment.getReceivedDate() != null, shipment.getStore().getId(),
					shipment.getVendor().getId(), shipment.getProduct().getId(), shipment.getQuantityOfItem() );
		}

		System.out.println("Getting all other Stores...");
		List<Store> otherStores = StoreDAO.getOtherStores(store);
		for (Store otherStore : otherStores) {
			System.out.printf("%d: email = %s, phone = %s, address = %s\n", otherStore.getId(), otherStore.getEmail(),
					otherStore.getPhoneNumber(), otherStore.getAddress());
		}

		// Testing PRODUCT_TYPE

		/*System.out.println("EXECUTING TESTS FOR PRODUCT TYPE");

		System.out.println("Inserting a new productType...");
		ProductTypeDAO.addProductType(new ProductType("Tia Type", 11));

		System.out.println("Getting all product types...");
		List<ProductType> allProductTypes = ProductTypeDAO.getAllProductTypes();
		for (ProductType type : allProductTypes) {
			System.out.printf("%d: type = %s, parent_id = %s\n", type.getId(), type.getType(), type.getParentTypeId());
		}

		System.out.println("Getting product type with id = 5...");
		ProductType type = ProductTypeDAO.getProductTypeById(5);
		System.out.printf("%d: type = %s, parent_id = %s\n", type.getId(), type.getType(), type.getParentTypeId());

		System.out.println("Updating My Product Type...");
		ProductTypeDAO.updateProductType(new ProductType(allProductTypes.size(), "Revisioned Type", 11));

		System.out.println("Getting last product type...");
		type = ProductTypeDAO.getProductTypeById(allProductTypes.size());
		System.out.printf("%d: type = %s, parent_id = %s\n", type.getId(), type.getType(), type.getParentTypeId());*/

		// Testing PRODUCT

		System.out.println("EXECUTING TESTS FOR PRODUCT");

		Brand brand = BrandDAO.getBrandById(5);
		Vendor vendor = VendorDAO.getVendorById(5);
		ProductType productType = ProductTypeDAO.getProductTypeById(1);

		System.out.println("Inserting a new Product...");
		ProductDAO.addProduct(new Product("Tias new product",
											brand, vendor, productType, store, 5.99, 5.99, "M", 16, "women"));

		System.out.println("Getting 50 Products");

		// Testing TRANSACTION

		// Testing SHIPMENT

		// Testing CUSTOMER
	}
}
