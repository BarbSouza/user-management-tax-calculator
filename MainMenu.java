/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (DatabaseSetup.setupDB()) {
            System.out.println("Database has been successfully created or already exists.");

            DatabaseWriter dbWriter = new DatabaseWriter(); // Writing to DB
            DatabaseReaderUser dbReaderUser = new DatabaseReaderUser(); // Reading user data
            DatabaseReaderAdmin dbReaderAdmin = new DatabaseReaderAdmin(); // Admin-specific data
            Scanner scanner = new Scanner(System.in);

            int choice = 0;
        while (choice !=3) {
                System.out.println("\nWelcome to the User Management System");
                System.out.println("1. Log in");
                System.out.println("2. Sign up (Regular User)");
                System.out.println("3. Exit");

                int mainChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (mainChoice) {
                    case 1: // Log in
                        System.out.println("Enter username:");
                        String username = scanner.nextLine();
                        System.out.println("Enter password:");
                        String password = scanner.nextLine();

                        User loggedInUser = dbReaderUser.loginUser(username, password);

                        if (loggedInUser != null) {
                            if (loggedInUser.getRole().equals("admin")) {
                                adminMenu(scanner, dbReaderAdmin, dbWriter, loggedInUser);
                            } else {
                                userMenu(scanner, dbReaderUser, dbWriter, loggedInUser);
                            }
                        } else {
                            System.out.println("Invalid credentials. Please try again.");
                        }
                        break;

                    case 2: // Sign up
                        System.out.println("Sign Up - Enter your details:");
                        System.out.println("Username: ");
                        String newUsername = scanner.nextLine();
                        System.out.println("Password: ");
                        String newPassword = scanner.nextLine();
                        System.out.println("Name: ");
                        String newName = scanner.nextLine();
                        System.out.println("Surname: ");
                        String newSurname = scanner.nextLine();

                        User newUser = new User(0, newUsername, newPassword, newName, newSurname); // ID will be auto-generated
                        if (dbWriter.addUser(newUser)) {
                            System.out.println("Sign-up successful! Please log in.");
                        } else {
                            System.out.println("Sign-up failed. Username might already exist.");
                        }
                        break;

                    case 3: // Exit
                        System.out.println("Thank you for using the system. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Error setting up the database. Please check your connection.");
        }
    }

    private static void adminMenu(Scanner scanner, DatabaseReaderAdmin dbReaderAdmin, DatabaseWriter dbWriter, User adminUser) throws SQLException {
        int choice = 0;
        while (choice !=4) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Modify Profile");
            System.out.println("2. View All Users");
            System.out.println("3. Remove a User");
            System.out.println("4. Logout");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1: // Modify Profile
                    System.out.println("Modify Admin Profile:");
                    System.out.println("New Username: ");
                    String newUsername = scanner.nextLine();
                    System.out.println("New Password: ");
                    String newPassword = scanner.nextLine();
                    System.out.println("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.println("New Surname: ");
                    String newSurname = scanner.nextLine();

                    if (dbWriter.modifyAdminProfile(adminUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
                        System.out.println("Profile updated successfully!");
                    } else {
                        System.out.println("Failed to update profile.");
                    }
                    break;

                case 2: // View All Users
                    ArrayList<User> users = dbReaderAdmin.getAllData();
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        System.out.printf("%-5s | %-20s | %-15s | %-15s\n", "ID", "Username", "Name", "Surname");
                        System.out.println("----------------------------------------------------");
                        for (User user : users) {
                            System.out.printf("%-5d | %-20s | %-15s | %-15s\n", user.getUserId(), user.getUsername(), user.getName(), user.getSurname());
                        }
                    }
                    break;

                case 3: // Remove a User
                    System.out.println("Enter the User ID to remove:");
                    int userIdToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (dbWriter.removeUser(userIdToRemove)) {
                        System.out.println("User removed successfully.");
                    } else {
                        System.out.println("Failed to remove user. User ID may not exist.");
                    }
                    break;

                case 4: // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userMenu(Scanner scanner, DatabaseReaderUser dbReaderUser, DatabaseWriter dbWriter, User loggedInUser) throws SQLException {
        int choice = 0;
        while (choice !=3) {
            System.out.println("\nUser Menu");
            System.out.println("1. Modify Profile");
            System.out.println("2. Check your finacial information");
            System.out.println("3. Logout");
            

            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (userChoice) {
                case 1: // Modify Profile
                    System.out.println("Modify Profile:");
                    System.out.println("New Username: ");
                    String newUsername = scanner.nextLine();
                    System.out.println("New Password: ");
                    String newPassword = scanner.nextLine();
                    System.out.println("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.println("New Surname: ");
                    String newSurname = scanner.nextLine();

                    if (dbWriter.modifyUserProfile(loggedInUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
                        System.out.println("Profile updated successfully!");
                    } else {
                        System.out.println("Failed to update profile.");
                    }
                    break;
                    
                    
                case 2:
                    System.out.println("Checking Your financial info...");
                    return;

                case 3: // Logout
                    System.out.println("Logging out...");
                    return;
                    

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

