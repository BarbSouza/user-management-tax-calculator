
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Main class of the application. Responsible for initializing the database and launching the main
 * menu of the system.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class Main {

  public static void main(String[] args)
      throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    // Attempt to set up the database (create it or verify its existence).
    if (DatabaseSetup.setupDB()) {
        System.out.println(
                " ______________________________________________________________________ \n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|____________________ User Management System ________________________|\n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|_ Database has been successfully created or already exists. ________|\n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|____________________________________________________________________|\n");
        
    } else{
        System.out.println(
                " ______________________________________________________________________ \n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|____________________ User Management System ________________________|\n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|_ Error setting up the database.  Please check your connection._____|\n"
                    + "|--------------------------------------------------------------------|\n"
                    + "|____________________________________________________________________|\n");
    }

    // Launch the main menu of the application.
    MainMenu mainMenu = new MainMenu();
    mainMenu.mainMenu();
  }
}
