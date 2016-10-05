package com.projectpainting.users;

import java.sql.*;

public class Validate {
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/projectpainting";

	// Database credentials
	private static final String USER = "root";
	private static final String PASS = "";

	public User findUser(String inputEmail, String inputPassword) {
		Connection conn = null;
		Statement stmt = null;
		User foundUser = null;
		try {
			// STEP 2. Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3. Open a connection
			//System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("Connected database succesfully...");

			// STEP 4. Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();

			ResultSet rs = null;

			// STEP 5. Extract data from result set
			//System.out.println("Fetching records with condition...");
			
			String sql = "SELECT id, name, password, email FROM users WHERE email = '" + inputEmail + "' AND password = '" + inputPassword
					+ "'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			
			rs.next(); 
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String password = rs.getString("password");
			String email = rs.getString("email");
			/*
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
			System.out.println("Password: " + password);
			System.out.println("Email: " + email);
			*/
			foundUser = new User(id, name, password, email);
			
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
		} finally {
			// finally block used to close resources
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		//System.out.println("Goodbye");
		return foundUser;

	}

	public boolean checkUser(User user) {
		if (user != null) {
			return true;
		}
		return false;
	}
	
	public User signUpUser(String inputName, String inputEmail, String inputPassword) {
		Connection conn = null;
		Statement stmt = null;
		User newUser = null;
		
		try {
			// STEP 2. Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3. Open a connection
			//System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("Connected database succesfully...");

			// STEP 4. Execute a query
			//System.out.println("Creating statement...");
			stmt = conn.createStatement();

			//ResultSet rs = null;

			// STEP 5. Extract data from result set
			System.out.println("Fetching records with condition...");
			
			String sql = "INSERT INTO users (name, password, email, datetime) VALUES ('" + inputName + "', '" + inputPassword + "', '" + inputEmail + "', '0000-00-00')";
			stmt.executeUpdate(sql);
			
			newUser = new User(0, inputName, inputPassword, inputEmail);
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
		} finally {
			// finally block used to close resources
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		//System.out.println("Goodbye");
		return newUser;
	}
}