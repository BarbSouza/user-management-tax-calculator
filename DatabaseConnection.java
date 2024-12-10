
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 */
public abstract class DatabaseConnection {
    //Here it will perform the database connection information
    //CRUD Operations rely on this connection
    
    
    protected final static String DB_BASE_URL = "jdbc:mysql://localhost";
    protected final static String USER = "ooc2023";
    protected final static String PASSWORD = "ooc2023";
    
    //This schema name may or may not have been created
    protected final static String DB_NAME = "user_management";
    protected final static String USER_TABLE = "user";
    
    // Now we create the final Database URL with the schema name 
    protected final static String DB_URL = DB_BASE_URL + "/" + DB_NAME;
    
    
    /*
  public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
*/
}
