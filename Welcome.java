
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
    public static void main(String[] args){
        
        //Ask the user for their username and welcome the user
        System.out.println("Enter your name: ");
        
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        
        System.out.println("Welcome " + name);
        
        scanner.close();
    }
    
}
