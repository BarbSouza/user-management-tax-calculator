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
* @author chrystiandybas
 */
public class DatabaseWriter extends DatabaseConnection{
    // THis method will write information to the databse 
    
     public boolean addUser(User user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = String.format("INSERT INTO %s (username, password, name, surname, role) VALUES ('%s', '%s', '%s', '%s', 'regular');",
                    USER_TABLE, user.getUsername(), user.getPassword(), user.getName(), user.getSurname());
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")){
            }else{
            e.printStackTrace();
            }
            return false;
        }
    }
    
    public boolean modifyUserProfile(int userId, String newUsername, String newPassword, String newName, String newSurname) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = String.format("UPDATE %s SET username='%s', password='%s', name='%s', surname='%s' WHERE userId=%d;",
                    USER_TABLE, newUsername, newPassword, newName, newSurname, userId);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeUser(int userId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = String.format("DELETE FROM %s WHERE userId=%d;", USER_TABLE, userId);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addTaxCalculation(TaxCalculation calculation) throws SQLException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         Statement stmt = conn.createStatement()) {

        String sql = String.format(
            "INSERT INTO tax_calculations (username, gross_income, tax_credits, income_tax, usc, prsi, total_tax) " +
            "VALUES ('%s', %.2f, %.2f, %.2f, %.2f, %.2f, %.2f);",
            calculation.getUsername(),
            calculation.getGrossIncome(),
            calculation.getTaxCredits(),
            calculation.getIncomeTax(),
            calculation.getUsc(),
            calculation.getPrsi(),
            calculation.getTotalTax()
        );

        stmt.executeUpdate(sql);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }
    
    public boolean modifyAdminProfile(int userId, String newUsername, String newPassword, String newName, String newSurname) throws SQLException {
        return modifyUserProfile(userId, newUsername, newPassword, newName, newSurname);
    }
    
    
    
}
