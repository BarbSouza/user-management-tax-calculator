
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 * @author chrystiandybas
 */
public class DatabaseReaderAdmin extends DatabaseConnection {
    //Read the user data to the admin from the databse
    //Now we will create a collection method to store and retrieve the data
    
    public ArrayList<User> getAllData() throws SQLException{
        
        ArrayList<User> users = new ArrayList<>();
        
        try(//Stabliching Connection with the database
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                    Statement stmt = conn.createStatement();
                    ){
            ResultSet results = stmt.executeQuery("SELECT * FROM " + USER_TABLE + ";");
            // Create a check for results and create a while loop to iterate through them
            while(results.next()){
                
                int userId = results.getInt("userId");
                String username = results.getString("username");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String role = results.getString("role");
                
                User user = new User(userId, username, "", name, surname, role);
                users.add(user);
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }
            
        
        
        return users;
        
    }
    
    public ArrayList<TaxCalculation> getUserOperations() throws SQLException {
        ArrayList<TaxCalculation> operations = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
                ){
            ResultSet results = stmt.executeQuery("SELECT * FROM " + TAX_TABLE + ";");
            while (results.next()) {
                int calculationId = results.getInt("calculationId");
                int userId = results.getInt("userId");
//                String username = results.getString("username");
                double grossIncome = results.getDouble("gross_income");
                double taxCredits = results.getDouble("tax_credits");
                double incomeTax = results.getDouble("income_tax");
                double usc = results.getDouble("usc");
                double prsi = results.getDouble("prsi");
                double totalTax = results.getDouble("total_tax");
                
                TaxCalculation newTax = new TaxCalculation(calculationId, userId, grossIncome, taxCredits, incomeTax, usc, prsi, totalTax);
                operations.add(newTax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }
       
}

