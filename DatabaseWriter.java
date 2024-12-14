import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class is responsible for handling write operations to the database, including adding,
 * updating, and removing users and tax calculations.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 *     <p>
 */
public class DatabaseWriter extends DatabaseConnection {

  /** Adds a new user to the database with the provided details. */
  public boolean addUser(User user) throws SQLException {
    DataTypeManipulation DTM = new DataTypeManipulation();

    String capitalisedName = DTM.capitaliseFirstLetter(user.getName());
    String capitalisedSurname = DTM.capitaliseFirstLetter(user.getSurname());

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
      String sql =
          String.format(
              "INSERT INTO %s (username, password, name, surname, role) VALUES ('%s', '%s', '%s', '%s', 'regular');",
              USER_TABLE,
              user.getUsername(),
              user.getPassword(),
              capitalisedName,
              capitalisedSurname);
      stmt.executeUpdate(sql);
      return true;
    } catch (SQLException e) {
      if (e.getSQLState().equals("23000")) {
        // Handle unique constraint violation (e.g., duplicate username).
      } else {
        e.printStackTrace();
      }
      return false;
    }
  }

  /** Modifies the profile of an existing user in the database. */
  public boolean modifyUserProfile(
      int userId, String newUsername, String newPassword, String newName, String newSurname)
      throws SQLException {

    DataTypeManipulation DTM = new DataTypeManipulation();

    String capitalisedName = DTM.capitaliseFirstLetter(newName);
    String capitalisedSurname = DTM.capitaliseFirstLetter(newSurname);

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
      String sql =
          String.format(
              "UPDATE %s SET username='%s', password='%s', name='%s', surname='%s' WHERE userId=%d;",
              USER_TABLE, newUsername, newPassword, capitalisedName, capitalisedSurname, userId);
      stmt.executeUpdate(sql);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  /** Removes all tax calculation records associated with a specific user. */
  public boolean removeUserTransactions(int userId) throws SQLException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
      String sql = String.format("DELETE FROM %s WHERE userId=%d;", TAX_TABLE, userId);
      stmt.executeUpdate(sql);
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  /** Removes a user from the database, including their tax calculation records. */
  public boolean removeUser(int userId) throws SQLException {

    int id = userId;
    removeUserTransactions(id);
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {
      String sql = String.format("DELETE FROM %s WHERE userId=%d;", USER_TABLE, userId);
      int contentDeleted = stmt.executeUpdate(sql);
      return contentDeleted > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  /** Adds a new tax calculation record to the database. */
  public boolean addTaxCalculation(TaxCalculation calculation) throws SQLException {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement()) {

      String sql =
          String.format(
              "INSERT INTO tax_calculations (userId, gross_income, tax_credits, income_tax, usc, prsi, total_tax) "
                  + "VALUES ('%s', %.2f, %.2f, %.2f, %.2f, %.2f, %.2f);",
              calculation.getUserId(),
              calculation.getGrossIncome(),
              calculation.getTaxCredits(),
              calculation.getIncomeTax(),
              calculation.getUsc(),
              calculation.getPrsi(),
              calculation.getTotalTax());

      stmt.executeUpdate(sql);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /** Modifies the profile of an admin user. */
  public boolean modifyAdminProfile(
      int userId, String newUsername, String newPassword, String newName, String newSurname)
      throws SQLException {
    return modifyUserProfile(userId, newUsername, newPassword, newName, newSurname);
  }
}
