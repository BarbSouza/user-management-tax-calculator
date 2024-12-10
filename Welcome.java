
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
 */
public class Welcome {
    
    //Create a main method to process the interaction with the user
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        //Using the db set up we can check if the operation went ok
        
        if(DatabaseSetup.setupDB()){
        //If after we run the setupDB method all is ok
        //we have now created a db schema and create a table
        
            System.out.println("Database has been successfully created or already exists.");
            //Instantiating the writer and reader for the db
            DatabaseWriter dbw = new DatabaseWriter(); //This will allow us to write to the db
            DatabaseReaderUser dbr = new DatabaseReaderUser(); //This will allow us to read from the db
            Scanner scanner = new Scanner(System.in); //Capture the user input
            
            //We want to present this menu to the user
            //and we want the user to interact for as long as they please
            while(true){
                //Like all menus
                //We need a couple of print statements
                //to present to the user
                System.out.println("\n Login System");
                System.out.println("Please Select from the Following Options\n");
                System.out.println("1. Insert a User to the Record");
                System.out.println("2. Read User from Database");
                System.out.println("3. EXIT");
                System.out.println("\nEnter your choice\n");
                
                //caputure the user choice
                int choice = scanner.nextInt();
                scanner.nextLine(); //add another line
                
                
                
                switch(choice){
                    
                    case 1: //if the user input ==1
                        //Insert new data to the db
                        System.out.println("Enter User Data");
                        System.out.println("username: ");
                        String username = scanner.nextLine(); 
                        System.out.println("password: ");
                        String password = scanner.nextLine();
                        System.out.println("name: ");
                        String name = scanner.nextLine();
                        System.out.println("surname: ");
                        String surname = scanner.nextLine();
                        
                        //collect all the user input
                        User newUser = new User(username, password, name, surname);
                        
                        
                        if(dbw.addUser(newUser)){
                            System.out.println("User added Successfully");
                            
                        }else {
                            System.out.println("Unable to add user, please check all the fields");
                        }
                        break;
                    case 2: //read the data from db
                        ArrayList<User> users =dbr.getAllData();
                        
                        if(users.isEmpty()){
                            System.out.println("No Data was found");
                        }else{
                            System.out.printf("%-5s | %-20s | %-12s | %8s | %-12s", "\nID", "Username", "Password", "Name", "Surname");
                            System.out.println("\n-------------------------------------------");
                            //print out all the patient records into the table
                            //this will require iterating through the 
                            for(User user: users){
                                System.out.printf("%-5s | %-20s | %-12s | %8s\n",
                                        user.getUsername(), 
                                        user.getPassword(),
                                        user.getName(), 
                                        user.getSurname());
                            }
                        }
                            break;
                            
                            case 3: //Exit
                                System.out.println("Thank you for using our system");
                                System.out.println("System Exit...");
                                scanner.close(); //IO Stream and we dont want the user to be able to interact with the system when it's shutdown
                                return;
                                
                            default:
                                System.out.println("Wrong input please select from the choices");
                            
                        }

                    }
            
        }else {
            //This means there is an issue either connecting to the db or creating it
            System.out.println("There was a problem creating or connecting to the databases. \nPlease check the db credentials");
        }

    }
    
}
