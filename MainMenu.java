
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 * @author chrystiandybas
 */
public class MainMenu {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (DatabaseSetup.setupDB()) {
            System.out.println("Database has been successfully created or already exists.");

            DatabaseWriter dbWriter = new DatabaseWriter(); // Writing to DB
            DatabaseReaderUser dbReaderUser = new DatabaseReaderUser(); // Reading user data
            DatabaseReaderAdmin dbReaderAdmin = new DatabaseReaderAdmin(); // Admin-specific data
            Scanner scanner = new Scanner(System.in);
            
            int choice = 0;
            while (choice != 3) {
                System.out.println(" ______________________________________________ \n"
                        +"|----------------------------------------------|\n"
                        +"|___ Welcome to the User Management System ___ |\n"
                        +"|----------------------------------------------|\n"
                        +"|___ Main menu ________________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|----------------------------------------------|\n"
                        +"|___ 1) Log in - ______________________________|\n"
                        +"|___ 2) Sing up (Regular User) - ______________|\n"
                        +"|___ 3) Exit - ________________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|----------------------------------------------|\n"
                        +"|______________________________________________|\n"
                       );

                int mainChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (mainChoice) {
                    case 1: // Log in
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Log In page ______________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter Username ----------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String username = scanner.nextLine();
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Log In page ______________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter Password ----------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String password = scanner.nextLine();

                        User loggedInUser = dbReaderUser.loginUser(username, password);

                        if (loggedInUser != null) {
                            if (loggedInUser.getRole().equals("admin")) {
                                adminMenu(scanner, dbReaderAdmin, dbWriter, loggedInUser);
                            } else {
                                userMenu(scanner, dbReaderUser, dbWriter, loggedInUser);
                            }
                        } else {
                            System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Log In page ______________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Invalid Credentials, please try again -----|\n"
                                        +"|______________________________________________|\n"
                                       );
                        }
                        break;

                    case 2: // Sign up
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sign Up - Enter your details _____________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter Username ----------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String newUsername = scanner.nextLine();
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sign Up - Enter your details _____________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter Password ----------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String newPassword = scanner.nextLine();
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sign Up - Enter your details _____________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter your name ---------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String newName = scanner.nextLine();
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sign Up - Enter your details _____________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Enter your surname ------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        String newSurname = scanner.nextLine();

                        User newUser = new User(0, newUsername, newPassword, newName, newSurname); // ID will be auto-generated
                        if (dbWriter.addUser(newUser)) {
                            System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sing up successful ! _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Please Log in -----------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        } else {
                            System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Sing up failed. __________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Username might already exist --------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        }
                        break;

                    case 3: // Exit
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Thank you for using the system. __________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|-- Goodbye!! ---------------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        scanner.close();
                        return;

                    default:
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Invalid Option ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Please try again -------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                        break;
                }
            }
        } else {
            System.out.println("Error setting up the database. Please check your connection.");
        }
    }

    private static void adminMenu(Scanner scanner, DatabaseReaderAdmin dbReaderAdmin, DatabaseWriter dbWriter, User adminUser) throws SQLException {
        
        int choice =0;
        while (choice != 4) {
            System.out.println(" ______________________________________________ \n"
                        +"|----------------------------------------------|\n"
                        +"|_________ User Management System ____________ |\n"
                        +"|----------------------------------------------|\n"
                        +"|___ Admin Menu _______________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|----------------------------------------------|\n"
                        +"|___ 1) Modify Profile - ______________________|\n"
                        +"|___ 2) View All Users - ______________________|\n"
                        +"|___ 3) Remove a User -________________________|\n"
                        +"|___ 4) View all tax information - ____________|\n"
                        +"|___ 5) Logout - ______________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|______________________________________________|\n"
                       );

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (adminChoice) {
                case 1:
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new username : --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newUsername = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new password : --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newPassword = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new name : ------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newName = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new surname : ---------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newSurname = scanner.nextLine();

                    if (dbWriter.modifyAdminProfile(adminUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Profile updated successfully -------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Admin Profile _____________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Failed to update profile -----------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    }
                    break;

                case 2: // View All Users
                    ArrayList<User> users = dbReaderAdmin.getAllData();
                    if (users.isEmpty()) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- No users found ---------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println(" _________________________________________________________________ \n"
                                        +"|------------------------------------------------------------------|\n"
                                        +"|___ User Management System _______________________________________|\n"
                                        +"|------------------------------------------------------------------|");
                        System.out.printf("| %-5s | %-20s | %-15s | %-15s |\n", "ID", "Username", "Name", "Surname");
                        System.out.println("|------------------------------------------------------------------|");
                        for (User user : users) {
                            System.out.printf("| %-5d | %-20s | %-15s | %-15s |\n", user.getUserId(), user.getUsername(), user.getName(), user.getSurname());
                        }
                        System.out.println("|__________________________________________________________________|");
                        
                                        
                    }
                    break;

                case 3: // Remove a User
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Enter the user ID to remove --------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    int userIdToRemove = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (dbWriter.removeUser(userIdToRemove)) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- User removed successfully ----------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Failed to remove user --------------------|\n"
                                        +"|--- User ID may not exist --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    }
                    break;
                case 4:
                    ArrayList<TaxCalculation> tax = dbReaderAdmin.getUserOperations();
                    if (tax.isEmpty()) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- No transactions found --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println("_______________________________________________________________________________________________________________ \n"
                                        +"|---------------------------------------------------------------------------------------------------------------|\n"
                                        +"|____________________ User Management System ___________________________________________________________________|\n"
                                        +"|---------------------------------------------------------------------------------------------------------------|\n"
                                        +"|------------- Transactions  -----------------------------------------------------------------------------------|\n"
                                        +"|---------------------------------------------------------------------------------------------------------------|");
                        System.out.printf("| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n", "Username", "Gross Income", "Tax Credits", "Income Tax", "USC", "PRSI", "Total Tax");
                        for (TaxCalculation newTax : tax) {
                        System.out.printf("| %-13s | %-13s | %-13s | %-13s | %-13s | %-13s | %-13s |\n",newTax.getUsername(), newTax.getGrossIncome(), newTax.getTaxCredits(), newTax.getIncomeTax(), newTax.getUsc(), newTax.getPrsi(), newTax.getTotalTax());
                                        }
                        System.out.println("|_______________________________________________________________________________________________________________| ");
                        
                        
                                        
                    }
                    break;
                    
                
                case 5: // Logout
                    return;

                default:
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Invalid option ---------------------------|\n"
                                        +"|--- Please try again -------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
            }
        }
    }

    private static void userMenu(Scanner scanner, DatabaseReaderUser dbReaderUser, DatabaseWriter dbWriter, User loggedInUser) throws SQLException {
        
        int choice = 0;
        while (choice != 2) {
            System.out.println(" ______________________________________________ \n"
                        +"|----------------------------------------------|\n"
                        +"|_________ User Management System _____________|\n"
                        +"|----------------------------------------------|\n"
                        +"|___ Regular User Menu ________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|----------------------------------------------|\n"
                        +"|___ 1) Modify Profile - ______________________|\n"
                        +"|___ 2) Check your finacial information - _____|\n"
                        +"|___ 3) Log Out - _____________________________|\n"
                        +"|----------------------------------------------|\n"
                        +"|______________________________________________|\n"
                       );

            int userChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch (userChoice) {
                case 1: 
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Profile ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new username : --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newUsername = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Profile ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new Password : --------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newPassword = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Profile ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new name : ------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newName = scanner.nextLine();
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify User Profile ______________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Insert new surname : ---------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    String newSurname = scanner.nextLine();

                    if (dbWriter.modifyUserProfile(loggedInUser.getUserId(), newUsername, newPassword, newName, newSurname)) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Profile ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Profile updated successfully -------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Modify Profile ___________________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Failed to update profile -----------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    }
                    break;
                case 2:
                    double grossIncome = 0;
                    double taxCredits = 0;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.println(" ______________________________________________ \n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ User Management System ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ Enter your gross income __________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|______________________________________________|\n"
                                              );
                            grossIncome = scanner.nextDouble();
                            validInput = true;
                        } catch (Exception e) {
                            System.out.println(" ______________________________________________ \n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ User Management System ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ ERROR: Please enter a valid number. ______|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|______________________________________________|\n"
                                              );
                            scanner.next();
                        }
                    }
                    validInput = false;
                    while (!validInput) {
                        try {
                            System.out.println(" ______________________________________________ \n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ User Management System ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ Enter your tax credits ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|______________________________________________|\n"
                                              );
                            taxCredits = scanner.nextDouble();
                            validInput = true; 
                        } catch (Exception e) {
                            System.out.println(" ______________________________________________ \n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ User Management System ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ ERROR: Please enter a valid number. ______|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|______________________________________________|\n"
                                              );
                            scanner.next();
                        }
                    }
                    TaxCalculator.TaxCalculationResult result = TaxCalculator.calculateTax(grossIncome, taxCredits);
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ Your Financial Information _______________|\n"
                                        +"|----------------------------------------------|"
                                       );
                    System.out.printf("| %-20s | %21.2f |\n", "Gross Income", grossIncome);
                    System.out.printf("| %-20s | %21.2f |\n", "Tax Credits", taxCredits);
                    System.out.printf("| %-20s | %21.2f |\n", "Income Tax (PAYE)", result.getIncomeTax());
                    System.out.printf("| %-20s | %21.2f |\n", "USC", result.getUsc());
                    System.out.printf("| %-20s | %21.2f |\n", "PRSI", result.getPrsi());
                    System.out.printf("| %-20s | %21.2f |\n", "Total Tax", result.getTotalTax());
                    System.out.println("|----------------------------------------------|\n"
                                      +"|______________________________________________|"
                                     );
                    TaxCalculation calculation = new TaxCalculation(
                        loggedInUser.getUsername(),
                        grossIncome,
                        taxCredits,
                        result.getIncomeTax(),
                        result.getUsc(),
                        result.getPrsi(),
                        result.getTotalTax()
                    );
                    if (dbWriter.addTaxCalculation(calculation)) {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|_ Financial information stored successfully __|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    } else {
                        System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System ___________________|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|_ Failed to store financial information ______|\n"
                                        +"|----------------------------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
                    }
                    break;
                case 3: 
                    return;
                default:
                    System.out.println(" ______________________________________________ \n"
                                        +"|----------------------------------------------|\n"
                                        +"|___ User Management System __________________ |\n"
                                        +"|----------------------------------------------|\n"
                                        +"|--- Invalid option ---------------------------|\n"
                                        +"|--- Please try again -------------------------|\n"
                                        +"|______________________________________________|\n"
                                       );
            }
        }
        
    }
    
}

