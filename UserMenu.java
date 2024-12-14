
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Displays the user menu and handles user input for various functionalities. Handles profile
 * modification, tax calculations, and displaying user information.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class UserMenu {

  /**
   * Displays the user menu and handles user input for various functionalities. Handles profile
   * modification, tax calculations, and displaying user information.
   */
  public static void userMenu(
      Scanner scanner, DatabaseReaderUser dbReaderUser, DatabaseWriter dbWriter, User loggedInUser)
      throws SQLException {

    int choice = 0;
    int userChoice = 0;
    while (choice != 2) {
      boolean validInput = false;

      // Displays the user menu and prompts for a valid user choice
      while (!validInput) {
        try {
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|_________ User Management System _____________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Regular User Menu ________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ 1) Modify Profile - ______________________|\n"
                  + "|___ 2) Tax calculation - _____________________|\n"
                  + "|___ 3) Check your profile information - ______|\n"
                  + "|___ 4) Check your financial history - ________|\n"
                  + "|___ 5) Log Out - _____________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|______________________________________________|\n");

          userChoice = scanner.nextInt();
          scanner.nextLine();
          validInput = true;

        } catch (Exception E) {
          // Handles invalid input and prompts for correct entry
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ ERROR: Please enter a valid choice. ______|\n"
                  + "|----------------------------------------------|\n"
                  + "|______________________________________________|\n");
          scanner.next();
        }
      }

      // Switch case to handle different user choices
      switch (userChoice) {
        case 1:
          // Prompt user for profile modification inputs (username, password, name, surname)
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Modify Profile ___________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|--- Insert new username : --------------------|\n"
                  + "|______________________________________________|\n");
          String newUsername = scanner.nextLine();
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Modify Profile ___________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|--- Insert new Password : --------------------|\n"
                  + "|______________________________________________|\n");
          String newPassword = scanner.nextLine();
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Modify Profile ___________________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|--- Insert new name : ------------------------|\n"
                  + "|______________________________________________|\n");
          String newName = scanner.nextLine();
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Modify User Profile ______________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|--- Insert new surname : ---------------------|\n"
                  + "|______________________________________________|\n");
          String newSurname = scanner.nextLine();

          // Modify the user profile with the new details and store the result
          if (dbWriter.modifyUserProfile(
              loggedInUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|___ Modify Profile ___________________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|--- Profile updated successfully -------------|\n"
                    + "|______________________________________________|\n");
          } else {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|___ Modify Profile ___________________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|--- Failed to update profile -----------------|\n"
                    + "|______________________________________________|\n");
          }
          break;
        case 2:
          // Request user input for gross income and tax credits, then calculate tax
          double grossIncome = 0;
          double taxCredits = 0;
          validInput = false;
          while (!validInput) {
            try {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ User Management System ___________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|___ Enter your gross income __________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|______________________________________________|\n");
              grossIncome = scanner.nextDouble();
              validInput = true;
            } catch (Exception e) {
              // Handles invalid input for income
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ User Management System ___________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|___ ERROR: Please enter a valid number. ______|\n"
                      + "|----------------------------------------------|\n"
                      + "|______________________________________________|\n");
              scanner.next();
            }
          }
          validInput = false;
          while (!validInput) {
            try {
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ User Management System ___________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|___ Enter your tax credits ___________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|______________________________________________|\n");
              taxCredits = scanner.nextDouble();
              validInput = true;
            } catch (Exception e) {
              // Handles invalid input for tax credits
              System.out.println(
                  " ______________________________________________ \n"
                      + "|----------------------------------------------|\n"
                      + "|___ User Management System ___________________|\n"
                      + "|----------------------------------------------|\n"
                      + "|___ ERROR: Please enter a valid number. ______|\n"
                      + "|----------------------------------------------|\n"
                      + "|______________________________________________|\n");
              scanner.next();
            }
          }
          // Calculate the taxes based on the inputs and store the result
          TaxCalculator.TaxCalculationResult result =
              TaxCalculator.calculateTax(grossIncome, taxCredits);
          System.out.println(
              " ______________________________________________ \n"
                  + "|----------------------------------------------|\n"
                  + "|___ User Management System ___________________|\n"
                  + "|----------------------------------------------|\n"
                  + "|___ Your Financial Information _______________|\n"
                  + "|----------------------------------------------|");
          System.out.printf("| %-20s | %21.2f |\n", "Gross Income", grossIncome);
          System.out.printf("| %-20s | %21.2f |\n", "Tax Credits", taxCredits);
          System.out.printf("| %-20s | %21.2f |\n", "Income Tax (PAYE)", result.getIncomeTax());
          System.out.printf("| %-20s | %21.2f |\n", "USC", result.getUsc());
          System.out.printf("| %-20s | %21.2f |\n", "PRSI", result.getPrsi());
          System.out.printf("| %-20s | %21.2f |\n", "Total Tax", result.getTotalTax());
          System.out.println(
              "|----------------------------------------------|\n"
                  + "|______________________________________________|");
          TaxCalculation calculation =
              new TaxCalculation(
                  loggedInUser.getUserId(),
                  grossIncome,
                  taxCredits,
                  result.getIncomeTax(),
                  result.getUsc(),
                  result.getPrsi(),
                  result.getTotalTax());
          if (dbWriter.addTaxCalculation(calculation)) {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|_ Financial information stored successfully __|\n"
                    + "|----------------------------------------------|\n"
                    + "|______________________________________________|\n");
          } else {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|_ Failed to store financial information ______|\n"
                    + "|----------------------------------------------|\n"
                    + "|______________________________________________|\n");
          }
          break;
        case 3:
          // Retrieves and displays the user's profile information
          User info = dbReaderUser.getUserData(loggedInUser.getUserId());

          if (info != null) {
            System.out.println(info);
          } else {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|_ No user found ______________________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|______________________________________________|\n");
          }
          break;
        case 4:
          // Retrieves and displays the user's financial history
          List<TaxCalculation> taxHistory =
              dbReaderUser.getUserFinancialData(loggedInUser.getUserId());

          if (taxHistory != null) {
            System.out.println(
                " __________________________________________________________________________________________________________________________________ \n"
                    + "|------------------------------------------------------------------------------------------------------------------------------------|\n"
                    + "|___ User Management System _________________________________________________________________________________________________________|\n"
                    + "|------------------------------------------------------------------------------------------------------------------------------------|\n"
                    + "|___ Your Financial Information _____________________________________________________________________________________________________|\n"
                    + "|------------------------------------------------------------------------------------------------------------------------------------|");

            System.out.printf(
                "| %-16s | %-16s | %-16s | %-16s | %-16s | %-16s | %-16s |\n",
                "Calculation ID",
                "Calculation Date",
                "Gross Income",
                "Tax Credits",
                "USC",
                "PRSI",
                "Total Tax");
            for (TaxCalculation taxCalculation : taxHistory) {
              System.out.println(taxCalculation);
            }
            System.out.println(
                "|____________________________________________________________________________________________________________________________________|");
          } else {
            System.out.println(
                " ______________________________________________ \n"
                    + "|----------------------------------------------|\n"
                    + "|___ User Management System ___________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|_ No financial data found ____________________|\n"
                    + "|----------------------------------------------|\n"
                    + "|______________________________________________|\n");
          }
          break;
        case 5:
          // Logs the user out and ends the loop
          choice = 2;
          break;
        default:
          System.out.println("Please choose a valid option!");
          break;
      }
    }
  }
}
