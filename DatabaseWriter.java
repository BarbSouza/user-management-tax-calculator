import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseWriter extends DatabaseConnection {

    // Method to add a new user
    public boolean addUser(User user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            String sql = String.format("INSERT INTO %s (username, password, name, surname, role) VALUES ('%s', '%s', '%s', '%s', 'regular');",
                    USER_TABLE, user.getUsername(), user.getPassword(), user.getName(), user.getSurname());
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to modify user profile (admin or regular user)
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

    // Method to remove a user by userId
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

    // Method to modify the admin profile
    public boolean modifyAdminProfile(int userId, String newUsername, String newPassword, String newName, String newSurname) throws SQLException {
        return modifyUserProfile(userId, newUsername, newPassword, newName, newSurname);
    }
}
