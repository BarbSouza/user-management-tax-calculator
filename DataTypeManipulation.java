/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Capitalizes the first letter of the given string.
 *
 * <p>Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class DataTypeManipulation {

  public String capitaliseFirstLetter(String input) {
    String s1 = input.substring(0, 1).toUpperCase();
    String firstLetterCapitalised = s1 + input.substring(1);
    return firstLetterCapitalised;
  }
}