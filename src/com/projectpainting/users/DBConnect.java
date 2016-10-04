package com.projectpainting.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/projectpainting";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "";
	
	public DBConnect() {

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 2. Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3. Open a connection
			// System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// System.out.println("Connected database succesfully...");

			stmt = conn.createStatement();

		} catch (SQLException err) {
			System.out.println(err.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
