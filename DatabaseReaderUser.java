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

public class DatabaseReaderUser extends DatabaseConnection {

    // Method to check if login credentials are valid for regular users
    public User loginUser(String username, String password) throws SQLException {
        User user = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = String.format("SELECT * FROM %s WHERE username='%s' AND password='%s';", USER_TABLE, username, password);
            ResultSet results = stmt.executeQuery(query);

            if (results.next()) {
                // User found, create User object
                int userId = results.getInt("userId");
                String name = results.getString("name");
                String surname = results.getString("surname");
                String role = results.getString("role");
                user = new User(userId, username, password, name, surname, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;  // Return null if no user found
    }

    // Method to get all user data (for listing users)
    public ArrayList<User> getAllData() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM " + USER_TABLE + ";");

            while (results.next()) {
                int userId = results.getInt("userId");
                String username = results.getString("username");
                String password = results.getString("password");
                String name = results.getString("name");
                String surname = results.getString("surname");

                User user = new User(userId, username, password, name, surname);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
