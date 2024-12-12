
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 * @author chrystiandybas
 */
public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (DatabaseSetup.setupDB()) {
            System.out.println("Database has been successfully created or already exists.");
        } else {
            System.out.println("Error setting up the database. Please check your connection.");
        }
        
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();
        
    }
}
