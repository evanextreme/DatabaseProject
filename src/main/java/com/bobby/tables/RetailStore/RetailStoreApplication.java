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
import java.util.Collections;
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

}
