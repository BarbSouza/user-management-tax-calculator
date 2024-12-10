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
    private String role;
    private String name;
    private String surname;
    
    //Constructors
    //Add the user to the database
    public User(int userId, String username, String password, String role, String name, String surname) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    //Read the user data to the admin
    //they can't see the user password
    public User(int userId, String username, String role, String name, String surname) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }
    
    //Read the user own data back to them so they can change it
    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
    
    
    
    //Getter and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    

}
