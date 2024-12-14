/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is responsible for setting up the database structure and ensuring essential tables
 * and default data are present. It handles creating the database, defining table schemas for
 * users and tax calculations, and adding default entries, such as an administrative user ('CCT').
 * Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class DatabaseSetup extends DatabaseConnection {
    
    
 public static boolean setupDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    
      
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            
            // try to connect to the databse 
            try(
                    Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    ){
                    // Creating the query (statement) 
                    // Create table if it doesn't exists
                    stmt.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + ";");
                    // Query the db using the USE
                    stmt.execute("USE " + DB_NAME + ";"); // database (Schema) pointer
                    // Create a query to inert into the db 
                    String userTableSQL = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
                        + "userId INT AUTO_INCREMENT PRIMARY KEY,"
                        + "username VARCHAR(50) NOT NULL UNIQUE,"
                        + "password VARCHAR(255) NOT NULL,"
                        + "role ENUM('admin', 'regular') NOT NULL,"
                        + "name VARCHAR(100),"
                        + "surname VARCHAR(100)"
                        + ");";
                    
                    // take this String query and execute it 
                    stmt.execute(userTableSQL);
                    
                    
                    String adminCheckSQL = "SELECT * FROM " + USER_TABLE + " WHERE username = 'CCT';";
                    ResultSet resultSet = stmt.executeQuery(adminCheckSQL);
                    if (!resultSet.next()) {
                    String adminInsertSQL = "INSERT INTO " + USER_TABLE + " (username, password, role, name, surname) VALUES ('CCT', 'Dublin', 'admin', 'System', 'Admin');";
                    stmt.executeUpdate(adminInsertSQL);
                    System.out.println("Admin user 'CCT' has been created.");
                    }
                    String taxTableSQL = "CREATE TABLE IF NOT EXISTS tax_calculations ("
                        + "calculationId INT AUTO_INCREMENT PRIMARY KEY, "
                        + "userId INT NOT NULL, "
                        + "gross_income DECIMAL(10, 2) NOT NULL, "
                        + "tax_credits DECIMAL(10, 2) NOT NULL, "
                        + "income_tax DECIMAL(10, 2) NOT NULL, "
                        + "usc DECIMAL(10, 2) NOT NULL, "
                        + "prsi DECIMAL(10, 2) NOT NULL, "
                        + "total_tax DECIMAL(10, 2) NOT NULL, "
                        + "calculation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                        + "FOREIGN KEY (userId) REFERENCES user(userId)"
                        + ");";
                    stmt.execute(taxTableSQL);
                    return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
    }
    
    
/*    
    // Main method to test the setup
    public static void main(String[] args) {
        try {
            boolean success = setupDB();
            if (success) {
                System.out.println("Setup completed successfully.");
            } else {
                System.err.println("Setup failed.");
            }
        } catch (Exception e) {
            System.err.println("An exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
 */   
}
