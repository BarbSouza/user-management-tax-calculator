import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
  * @author chrystiandybas
 */
public class DatabaseReaderUser extends DatabaseConnection{
    
    
    
    public User loginUser(String username, String password) throws SQLException {
        User user = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE username='%s' AND password='%s';", USER_TABLE, username, password);
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                // User found, create User object
                int userId = results.getInt("userId");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String role = results.getString("role");
                user = new User(userId, username, password, name, surname, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    //Read the user data to the user from the database
    //Now we will create a collection method to store and retrieve the data
    
    
        
    
    public User getUserData(int userId) throws SQLException {
        User user = null;

        try (
            // Establishing connection with the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM " + USER_TABLE + " WHERE userId = ?");
        ) {
            // Setting the userId parameter
            pstmt.setInt(1, userId);

            ResultSet results = pstmt.executeQuery();
            // If a user is found, extract their data
            if (results.next()) {
                String username = results.getString("username");
                String password = results.getString("password");
                String name = results.getString("name");
                String surname = results.getString("surname");

                user = new User(userId, username, password, name, surname);
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
        public List<TaxCalculation> getUserFinancialData(int userId) throws SQLException {
        List<TaxCalculation> userFinancialData = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM " + TAX_TABLE + " WHERE userId = ?");
        ) {
            pstmt.setInt(1, userId);
            ResultSet results = pstmt.executeQuery();

            while (results.next()) {
                int calculationId = results.getInt("calculationId");
                double gross_income = results.getDouble("gross_income");
                double tax_credits = results.getDouble("tax_credits");
                double income_tax = results.getDouble("income_tax");
                double usc = results.getDouble("usc");
                double prsi = results.getDouble("prsi");
                double total_tax = results.getDouble("total_tax");

                java.sql.Date calculation_date = results.getDate("calculation_date");
                String calculationDateStr = calculation_date != null ? calculation_date.toString() : null;

                // Create a new TaxCalculation object for each row
                TaxCalculation taxCalculation = new TaxCalculation(
                    userId, calculationId, gross_income, tax_credits, income_tax, usc, prsi, total_tax, calculationDateStr
                );

                // Add the TaxCalculation object to the list
                userFinancialData.add(taxCalculation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userFinancialData;
    }
    
}
