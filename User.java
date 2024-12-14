/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class User {

  private int userId;
  private String username;
  private String password;
  private String role;
  private String name;
  private String surname;

  /** Used when all user details, including role, are available. */
  public User(
      int userId, String username, String password, String name, String surname, String role) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.role = role;
  }

  /** Constructor to initialize a user without specifying the role. */
  public User(int userId, String username, String password, String name, String surname) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.role = "regular";
  }

  /** Constructor to initialize a user with only basic details. */
  public User(String username, String password, String name, String surname) {
    this.userId = 0;
    this.password = password;
    this.name = name;
    this.surname = surname;
    this.role = "regular";
  }

  /** Gets the user's unique identifier. */
  public int getUserId() {
    return userId;
  }

  /** Gets the user's username. */
  public String getUsername() {
    return username;
  }

  /** */
  public String getPassword() {
    return password;
  }

  /** Gets the user's first name. */
  public String getName() {
    return name;
  }

  /** Gets the user's last name. */
  public String getSurname() {
    return surname;
  }

  /** Gets the user's role in the system. */
  public String getRole() {
    return role;
  }

  /** Sets the user's unique identifier. */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /** Sets the user's username. */
  public void setUsername(String username) {
    this.username = username;
  }

  /** Sets the user's password. */
  public void setPassword(String password) {
    this.password = password;
  }

  /** Sets the user's first name. */
  public void setName(String name) {
    this.name = name;
  }

  /** Sets the user's last name. */
  public void setSurname(String surname) {
    this.surname = surname;
  }

  /** Sets the user's role in the system. */
  public void setRole(String role) {
    this.role = role;
  }

  /** Generates a string representation of the user. */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(
            String.format(
                "| %-5s | %-13s | %-13s | %-13s | %-13s |\n",
                "ID", "Username", "Name", "Surname", "Password"))
        .append(
            "|------------------------------------------------------------------------------------|\n")
        .append(
            String.format(
                "| %-5d | %-13s | %-13s | %-13s | %-13s |\n",
                userId, username, name, surname, password));
    return sb.toString();
  }
}
