
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Barbara
 */

public class DatabaseReaderAdmin extends DatabaseConnection {

    // Method to get all users for admin to view
    public ArrayList<User> getAllData() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM " + USER_TABLE + ";");

            while (results.next()) {
                int userId = results.getInt("userId");
                String username = results.getString("username");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String role = results.getString("role");

                User user = new User(userId, username, "", name, surname, role);  // Password is not needed here
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Method to fetch a list of user operations performed (if you have a logging table)
    public ArrayList<String> getUserOperations() throws SQLException {
        ArrayList<String> operations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = "SELECT * FROM user_operations";  // Assuming a table for user operations
            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                operations.add(results.getString("operation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operations;
    }
}

