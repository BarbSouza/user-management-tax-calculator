
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 */
public class DatabaseWriter extends DatabaseConnection{
    // THis method will write information to the databse 
    
    public boolean addUser(User user) throws SQLException {
//    The patient instance == (name, birthdate, bloodtype, id)
    try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ){
                // Inser tdata into table ISERT INTO TABLE cols VALUES(?,?,?)
                String userTableSQL = String.format("INSERT INTO " + USER_TABLE + " VALUES ("
                    + "'%s', '%s', '%s', %s);",
                        user.getUsername(), 
                        user.getPassword(),
                        user.getName(), 
                        user.getSurname()
                    );
                stmt.execute(userTableSQL);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
    }  
}
