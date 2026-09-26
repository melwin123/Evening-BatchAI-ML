package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println( rs.getInt(1) > 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return false;

	}

}
