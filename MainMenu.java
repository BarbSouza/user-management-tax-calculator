
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Main menu class of the application. Handles user interactions for logging in, signing up, and
 * exiting the system. Serves as the entry point for navigation.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class MainMenu {

  public static void mainMenu() throws SQLException {
    Scanner scanner = new Scanner(System.in);
    DatabaseReaderUser dbReaderUser = new DatabaseReaderUser();
    DatabaseWriter dbWriter = new DatabaseWriter();
    DatabaseReaderAdmin dbReaderAdmin = new DatabaseReaderAdmin();
    AdminMenu adminMenu = new AdminMenu();
    UserMenu userMenu = new UserMenu();

    int choice = 0;
    int mainChoice = 0;

    // Main menu loop - keeps running until the user chooses to exit.
    while (choice != 3) {
      boolean validInput = false;

      // Loop to ensure valid input from the user.
      while (!validInput) {
        try {
          // Display the main menu to the user.
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ Welcome to the User Management System ___ |\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Main menu ________________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ 1) Log in - ______________________________|\n"
                  + "|___ 2) Sign up - _____________________________|\n"
                  + "|___ 3) Exit - ________________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|______________________________________________|\n");
          mainChoice = scanner.nextInt();
          scanner.nextLine(); // Consume newline character
          validInput = true;
        } catch (Exception e) {
          // Handle invalid input and prompt the user to try again.
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ ERROR: Please enter a valid choice. ______|\n"
                  + "|______________________________________________|\n");
          scanner.next();
        }
      }

      // Process the user's menu choice.
      switch (mainChoice) {
        case 1: // Log in
          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter Username ---------------------------|\n"
                  + "|______________________________________________|\n");
          String username = scanner.nextLine();

          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter Password ---------------------------|\n"
                  + "|______________________________________________|\n");
          String password = scanner.nextLine();

          // Attempt to log in the user with the provided credentials.
          User loggedInUser = dbReaderUser.loginUser(username, password);

          if (loggedInUser != null) {
            // Redirect based on user role: admin or regular user.
            if (loggedInUser.getRole().equals("admin")) {
              adminMenu.adminMenu(scanner, dbReaderAdmin, dbWriter, loggedInUser);
            } else {
              userMenu.userMenu(scanner, dbReaderUser, dbWriter, loggedInUser);
            }
          } else {
            // Handle invalid login credentials.
            System.out.println(
                " ______________________________________________ \n"
                    + "|--- Invalid Credentials, please try again ----|\n"
                    + "|______________________________________________|\n");
          }
          break;

        case 2: // Sign up
          // Collect user information for registration.
          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter Username ---------------------------|\n"
                  + "|______________________________________________|\n");
          String newUsername = scanner.nextLine();

          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter Password ---------------------------|\n"
                  + "|______________________________________________|\n");
          String newPassword = scanner.nextLine();

          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter your name --------------------------|\n"
                  + "|______________________________________________|\n");
          String newName = scanner.nextLine();

          System.out.println(
              " ______________________________________________ \n"
                  + "|--- Enter your surname -----------------------|\n"
                  + "|______________________________________________|\n");
          String newSurname = scanner.nextLine();

          // Create a new user object and attempt to save it in the database.
          User newUser =
              new User(0, newUsername, newPassword, newName, newSurname); // ID auto-generated
          if (dbWriter.addUser(newUser)) {
            System.out.println(
                " ______________________________________________ \n"
                    + "|___ Sign up successful! ______________________|\n"
                    + "|--- Please log in ----------------------------|\n"
                    + "|______________________________________________|\n");
          } else {
            System.out.println(
                " ______________________________________________ \n"
                    + "|___ Sign up failed. __________________________|\n"
                    + "|--- Username might already exist -------------|\n"
                    + "|______________________________________________|\n");
          }
          break;

        case 3: // Exit
          // Display exit message and close the scanner.
          System.out.println(
              " ______________________________________________ \n"
                  + "|___ Thank you for using the system. __________|\n"
                  + "|--- Goodbye! ---------------------------------|\n"
                  + "|______________________________________________|\n");
          scanner.close();
          return;

        default:
          // Handle invalid menu options.
          System.out.println(
              " ______________________________________________ \n"
                  + "|___ Invalid Option ___________________________|\n"
                  + "|--- Please try again -------------------------|\n"
                  + "|______________________________________________|\n");
          break;
      }
    }
  }
}

