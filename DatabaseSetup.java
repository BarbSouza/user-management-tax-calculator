/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Barbara
 */
public class DatabaseSetup extends DatabaseConnection {
    //Create logic to procces the database creation and make use of the databe information
    
 
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
                    String userTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                    + "userId INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL UNIQUE,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "role ENUM('admin', 'regular') NOT NULL,"
                    + "name VARCHAR(100),"
                    + "surname VARCHAR(100)"
                    + ");";
                    
                    // take this String query and execute it 
                    stmt.execute(userTableSQL);
                    
                    return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
    }
    
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
    
    
}
    
// create some logic to ensure we do not run into issues with the db connection
    
