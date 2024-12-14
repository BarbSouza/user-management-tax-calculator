/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class defines the database connection parameters for the user management system. It includes
 * the base URL, user credentials, and table names for the user and tax calculations. The final DB
 * URL combines the base URL and schema name to establish the connection to the database.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class DatabaseConnection {

  // Base URL for the MySQL database connection
  protected static final String DB_BASE_URL = "jdbc:mysql://localhost";

  // Database credentials
  protected static final String USER = "ooc2023";
  protected static final String PASSWORD = "ooc2023";

  // Database name and tables
  protected static final String DB_NAME = "user_management";
  protected static final String USER_TABLE = "user";
  protected static final String TAX_TABLE = "tax_calculations";

  // Final Database URL constructed by combining base URL and database name
  protected static final String DB_URL = DB_BASE_URL + "/" + DB_NAME;

    
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
