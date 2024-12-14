/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class TaxCalculation {

  // Fields for storing tax calculation details.
  private int calculationId;
  private int userId;
  private String calculationDateStr;
  private double grossIncome, taxCredits, incomeTax, usc, prsi, totalTax;

  /** Constructor for initializing a TaxCalculation object without a calculation ID or date. */
  public TaxCalculation(
      int userId,
      double grossIncome,
      double taxCredits,
      double incomeTax,
      double usc,
      double prsi,
      double totalTax) {
    this.userId = userId;
    this.grossIncome = grossIncome;
    this.taxCredits = taxCredits;
    this.incomeTax = incomeTax;
    this.usc = usc;
    this.prsi = prsi;
    this.totalTax = totalTax;
  }

  /** Constructor for initializing a TaxCalculation object with a calculation ID. */
  public TaxCalculation(
      int calculationId,
      int userId,
      double grossIncome,
      double taxCredits,
      double incomeTax,
      double usc,
      double prsi,
      double totalTax) {
    this.calculationId = calculationId;
    this.userId = userId;
    this.grossIncome = grossIncome;
    this.taxCredits = taxCredits;
    this.incomeTax = incomeTax;
    this.usc = usc;
    this.prsi = prsi;
    this.totalTax = totalTax;
  }

  /** Constructor for initializing a TaxCalculation object with all details. */
  public TaxCalculation(
      int calculationId,
      int userId,
      double grossIncome,
      double taxCredits,
      double incomeTax,
      double usc,
      double prsi,
      double totalTax,
      String calculationDateStr) {
    this.calculationId = calculationId;
    this.userId = userId;
    this.grossIncome = grossIncome;
    this.taxCredits = taxCredits;
    this.incomeTax = incomeTax;
    this.usc = usc;
    this.prsi = prsi;
    this.totalTax = totalTax;
    this.calculationDateStr = calculationDateStr;
  }

  /** Returns the date of this tax calculation as a string. */
  public String getCalculationDateStr() {
    return calculationDateStr;
  }

  /** Updates the calculation date string for this tax calculation. */
  public void setCalculationDateStr(String calculationDate) {
    this.calculationDateStr = calculationDate;
  }

  /** Returns the unique identifier for this tax calculation. */
  public int getCalculationId() {
    return calculationId;
  }

  /** Updates the unique identifier for this tax calculation. */
  public void setCalculationId(int calculationId) {
    this.calculationId = calculationId;
  }

  /** Returns the ID of the user associated with this calculation. */
  public int getUserId() {
    return userId;
  }

  /** Updates the ID of the user associated with this calculation. */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /** Returns the user's total gross income. */
  public double getGrossIncome() {
    return grossIncome;
  }

  /** Returns the tax credits available to the user */
  public double getTaxCredits() {
    return taxCredits;
  }

  /** Returns the calculated income tax for the user. */
  public double getIncomeTax() {
    return incomeTax;
  }

  /** Returns the Universal Social Charge for the user. */
  public double getUsc() {
    return usc;
  }

  /** Returns the Pay-Related Social Insurance for the user. */
  public double getPrsi() {
    return prsi;
  }

  /** Returns the total tax owed by the user. */
  public double getTotalTax() {
    return totalTax;
  }

  /** Provides a formatted string representation of the tax calculation. */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(
            "|------------------------------------------------------------------------------------------------------------------------------------| \n")
        .append(
            String.format(
                "| %-16s | %-16s | %-16s | %-16s | %-16s | %-16s | %-16s |\n",
                calculationId, calculationDateStr, grossIncome, taxCredits, usc, prsi, totalTax))
        .append(
            "|------------------------------------------------------------------------------------------------------------------------------------|\n");
    return sb.toString();
  }
}
