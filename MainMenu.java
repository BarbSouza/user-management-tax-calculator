
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static void mainMenu() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            DatabaseReaderUser dbReaderUser = new DatabaseReaderUser();
            DatabaseWriter dbWriter = new DatabaseWriter();
            DatabaseReaderAdmin dbReaderAdmin = new DatabaseReaderAdmin();
            AdminMenu adminMenu = new AdminMenu();
            UserMenu userMenu = new UserMenu();
            int choice = 0;
            int mainChoice = 0;
            while (choice != 3) {
                boolean validInput = false;
                    while (!validInput) {
                        try{
                            System.out.println(" ______________________________________________ \n"
                                    +"|----------------------------------------------|\n"
                                    +"|___ Welcome to the User Management System ___ |\n"
                                    +"|----------------------------------------------|\n"
                                    +"|___ Main menu ________________________________|\n"
                                    +"|----------------------------------------------|\n"
                                    +"|----------------------------------------------|\n"
                                    +"|___ 1) Log in - ______________________________|\n"
                                    +"|___ 2) Sing up - _____________________________|\n"
                                    +"|___ 3) Exit - ________________________________|\n"
                                    +"|----------------------------------------------|\n"
                                    +"|----------------------------------------------|\n"
                                    +"|______________________________________________|\n"
                                   );
                                    mainChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline character
                                    validInput = true;
                        }catch (Exception e){
                            System.out.println(" ______________________________________________ \n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ User Management System ___________________|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|___ ERROR: Please enter a valid choice. ______|\n"
                                               + "|----------------------------------------------|\n"
                                               + "|______________________________________________|\n"
                                              );
                            scanner.next();
                        }
                   }
                    
                    
                    
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
                                adminMenu.adminMenu(scanner, dbReaderAdmin, dbWriter, loggedInUser);
                            } else {
                                userMenu.userMenu(scanner, dbReaderUser, dbWriter, loggedInUser);
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
    }
    
}

