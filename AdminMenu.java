
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Displays the admin menu and handles actions based on user input.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class AdminMenu {

  public static void adminMenu(
      Scanner scanner, DatabaseReaderAdmin dbReaderAdmin, DatabaseWriter dbWriter, User adminUser)
      throws SQLException {

    int choice = 0;
    int adminChoice = 0;
    while (choice != 4) {
      boolean validInput = false;

      // Input validation for admin menu options
      while (!validInput) {
        try {
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|_________ User Management System ____________ |\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Admin Menu _______________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ 1) Modify Profile - ______________________|\n"
                  + "|___ 2) View All Users - ______________________|\n"
                  + "|___ 3) Remove a User -________________________|\n"
                  + "|___ 4) View all tax information - ____________|\n"
                  + "|___ 5) Logout - ______________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|______________________________________________|\n");

          adminChoice = scanner.nextInt();
          scanner.nextLine();
          validInput = true;
        } catch (Exception E) {
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ ERROR: Please enter a valid choice. ______|\n"
                  + "|----------------------------------------------|\n"
                  + "|______________________________________________|\n");
          scanner.nextLine();
        }

        // Switch case for admin actions
        switch (adminChoice) {
          case 1:
            // Modify admin profile
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ Modify Admin Profile _____________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|--- Insert new username : --------------------|\n"
                    + "|______________________________________________|\n");
            String newUsername = scanner.nextLine();
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|--- Insert new password : --------------------|\n"
                    + "|______________________________________________|\n");
            String newPassword = scanner.nextLine();
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|--- Insert new name : ------------------------|\n"
                    + "|______________________________________________|\n");
            String newName = scanner.nextLine();
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|--- Insert new surname : ---------------------|\n"
                    + "|______________________________________________|\n");
            String newSurname = scanner.nextLine();

            // Update the admin profile in the database
            if (dbWriter.modifyAdminProfile(
                adminUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ Profile updated successfully -------------|\n"
                      + "|______________________________________________|\n");
            } else {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ Failed to update profile -----------------|\n"
                      + "|______________________________________________|\n");
            }
            break;

          case 2: // View all users
            ArrayList<User> users = dbReaderAdmin.getAllData();
            if (users.isEmpty()) {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|___ No users found ---------------------------|\n"
                      + "|______________________________________________|\n");
            } else {
              // Display the list of users
              System.out.println(
                  " _________________________________________________________________ \n"
                      + "|------------------------------------------------------------------|\n"
                      + "|___ User Management System _______________________________________|\n"
                      + "|------------------------------------------------------------------|");
              System.out.printf(
                  "| %-5s | %-20s | %-15s | %-15s |\n", "ID", "Username", "Name", "Surname");
              System.out.println(
                  "|------------------------------------------------------------------|");
              for (User user : users) {
                System.out.printf(
                    "| %-5d | %-20s | %-15s | %-15s |\n",
                    user.getUserId(), user.getUsername(), user.getName(), user.getSurname());
              }
              System.out.println(
                  "|__________________________________________________________________|");
            }
            break;

          case 3: // Remove a user
            System.out.println(
                " ______________________________________________ \n"
                    + "|--- Enter the user ID to remove --------------|\n"
                    + "|______________________________________________| \n");
            int userIdToRemove = -1; // Initialize with an invalid value
            boolean validID = false;

            // Loop until a valid integer is entered
            while (!validID) {
                if (scanner.hasNextInt()) {
                    userIdToRemove = scanner.nextInt();
                    validID = true; // Exit the loop
                    scanner.nextLine(); // Consume newline
                } else {
                    System.out.println(
                        " ______________________________________________ \n"
                            + "|--- Invalid input. Please enter a valid ID ---|\n"
                            + "|______________________________________________| \n");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            // Remove the user from the database
            if (dbWriter.removeUser(userIdToRemove)) {
                System.out.println(
                    " ______________________________________________ \n"
                        + "|--- User removed successfully ----------------|\n"
                        + "|______________________________________________| \n");
            } else {
                System.out.println(
                    " ________________ \n"
                        + "|--- Failed to remove user --------------------|\n"
                        + "|--- User ID may not exist --------------------|\n"
                        + "|______________________________________________| \n");
            }
            break;
          case 4: // View tax information
            ArrayList<TaxCalculation> tax = dbReaderAdmin.getUserOperations();
            if (tax.isEmpty()) {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|--- No transactions found --------------------|\n"
                      + "|______________________________________________|\n");
            } else {
              // Display tax transactions
              System.out.println(
                  "_______________________________________________________________________________________________________________ \n"
                      + "|---------------------------------------------------------------------------------------------------------------|\n"
                      + "|____________________ User Management System ___________________________________________________________________|\n"
                      + "|---------------------------------------------------------------------------------------------------------------|\n"
                      + "|------------- Transactions  -----------------------------------------------------------------------------------|\n"
                      + "|---------------------------------------------------------------------------------------------------------------|");
              System.out.printf(
                  "| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n",
                  "User ID",
                  "Gross Income",
                  "Tax Credits",
                  "Income Tax",
                  "USC",
                  "PRSI",
                  "Total Tax");
              for (TaxCalculation newTax : tax) {
                System.out.printf(
                    "| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n",
                    newTax.getUserId(),
                    newTax.getGrossIncome(),
                    newTax.getTaxCredits(),
                    newTax.getIncomeTax(),
                    newTax.getUsc(),
                    newTax.getPrsi(),
                    newTax.getTotalTax());
              }
              System.out.println(
                  "|_______________________________________________________________________________________________________________| ");
            }
            break;

          case 5: // Logout
            return;

          default:
            // Invalid option input
            System.out.println(
                " ______________________________________________ \n"
                    + "|--- Invalid option ---------------------------|\n"
                    + "|--- Please try again -------------------------|\n"
                    + "|______________________________________________|\n");
        }
      }
    }
  }
}
