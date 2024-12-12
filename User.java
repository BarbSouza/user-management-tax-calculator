/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 */
public class User {
    //Creating the user Class to handle the user information that will be utilised by the system to add or retrieve the user data from the management database
    
    //Atributtes
    private int userId;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String role;
    
 //Constructors
    //Add the user to the database
    public User(int userId, String username, String password, String name, String surname, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    // Constructor for regular users (without role)
    public User(int userId, String username, String password, String name, String surname) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = "regular";
    }

    // Constructor for new users (no id and role)
    public User(String username, String password, String name, String surname) {
        this.userId = 0;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = "regular";
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }
        @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %-5s | %-13s | %-13s | %-13s | %-13s |\n", "ID", "Username", "Name", "Surname", "Password"))
          .append("|------------------------------------------------------------------------------------|\n")
          .append(String.format("| %-5d | %-13s | %-13s | %-13s | %-13s |\n", userId, username, name, surname, password));
        return sb.toString();
    }
  
}
