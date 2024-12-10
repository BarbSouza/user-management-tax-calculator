
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 */
public class DatabaseReaderAdmin extends DatabaseConnection {
    //Read the user data to the admin from the databse
    //Now we will create a collection method to store and retrieve the data
    
    public ArrayList<User> getAllData() throws SQLException{
        
        try(
                    Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    ){
            
            
        }
        
        return users;
        
    }
    
}
