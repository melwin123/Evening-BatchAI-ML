package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/bfsi_db";
	    String dbUser = "root";
	    String dbPass = "password";

	    String query = "SELECT COUNT(*) FROM app_user WHERE username = ? AND password = ?";
		
		//String query = "SELECT COUNT(*) FROM app_user WHERE username = ? AND password = ?";

	    try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
	             PreparedStatement pstmt = conn.prepareStatement(query)) {

	            pstmt.setString(1, "admin");
	            pstmt.setString(2, "password");

	            // Execute login check query
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    System.out.println(rs.getInt(1) > 0);
	                    
	                    System.out.println("=== 1. APP USERS TABLE ===");
	                    // Use a separate statement and distinct variable name (rsAppUsers)
	                    try (Statement stmt = conn.createStatement();
	                         ResultSet rsAppUsers = stmt.executeQuery("SELECT * FROM app_user")) {
	                        while (rsAppUsers.next()) {
	                            System.out.println("User ID: " + rsAppUsers.getLong("user_id") +
	                                               ", Username: " + rsAppUsers.getString("username") +
	                                               ", Role: " + rsAppUsers.getString("role"));
	                        }
	                    }

	                    System.out.println("\n=== 2. CUSTOMERS TABLE ===");
	                    try (Statement stmt = conn.createStatement();
	                         ResultSet rsCustomers = stmt.executeQuery("SELECT * FROM customer")) {
	                        while (rsCustomers.next()) {
	                            System.out.println("Customer ID: " + rsCustomers.getLong("customer_id") +
	                                               ", Name: " + rsCustomers.getString("name") +
	                                               ", Email: " + rsCustomers.getString("email") +
	                                               ", City: " + rsCustomers.getString("city") +
	                                               ", PAN: " + rsCustomers.getString("pan_number"));
	                        }
	                    }

	                    System.out.println("\n=== 3. ACCOUNTS TABLE ===");
	                    try (Statement stmt = conn.createStatement();
	                         ResultSet rsAccounts = stmt.executeQuery("SELECT * FROM account")) {
	                        while (rsAccounts.next()) {
	                            System.out.println("Account ID: " + rsAccounts.getLong("account_id") +
	                                               ", Number: " + rsAccounts.getString("account_number") +
	                                               ", Type: " + rsAccounts.getString("account_type") +
	                                               ", Balance: " + rsAccounts.getDouble("balance") +
	                                               ", Customer ID: " + rsAccounts.getLong("customer_id"));
	                        }
	                    }

	                    System.out.println("\n=== 4. LOANS TABLE ===");
	                    try (Statement stmt = conn.createStatement();
	                         ResultSet rsLoans = stmt.executeQuery("SELECT * FROM loan")) {
	                        while (rsLoans.next()) {
	                            System.out.println("Loan ID: " + rsLoans.getLong("loan_id") +
	                                               ", Type: " + rsLoans.getString("loan_type") +
	                                               ", Principal: " + rsLoans.getDouble("principal_amount") +
	                                               ", Interest Rate: " + rsLoans.getDouble("interest_rate") + "%" +
	                                               ", Tenure (Months): " + rsLoans.getInt("tenure_months") +
	                                               ", Customer ID: " + rsLoans.getLong("customer_id"));
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        //return false;
	}

}
