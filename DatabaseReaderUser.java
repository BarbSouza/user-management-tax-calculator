
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class DatabaseReaderUser extends DatabaseConnection{
    //Read the user data to the user from the database
    //Now we will create a collection method to store and retrieve the data
    
    public ArrayList<User> getAllData() throws SQLException{
        
        ArrayList<User> users = new ArrayList<>();
        
        try(//Stabliching Connection with the database
                    Connection conn = DriverManager.getConnection(DB_BASE_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    ){
            ResultSet results = stmt.executeQuery(String.format("SELECT * FROM %s;", USER_TABLE));
            // Create a check for results and create a while loop to iterate through them
            while(results.next()){
               
                String username = results.getString("username");
                String password = results.getString("password");
                String name = results.getString("name");
                String surname = results.getString("surname");
                
                User user = new User(username, password, name, surname);
                users.add(user);
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
        
        return users;
        
    }
    
}
