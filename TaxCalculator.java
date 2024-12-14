/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Authors:
 *
 * @chrystiandybas @Barbara @Heloi @Matheus
 */
public class TaxCalculator {

  public static class TaxCalculationResult {
    private double incomeTax;
    private double usc;
    private double prsi;
    private double totalTax;

    // Constructor to initialize tax calculation results.
    public TaxCalculationResult(double incomeTax, double usc, double prsi, double totalTax) {
      this.incomeTax = incomeTax;
      this.usc = usc;
      this.prsi = prsi;
      this.totalTax = totalTax;
    }

    // Getter for income tax value.
    public double getIncomeTax() {
      return incomeTax;
    }

    // Getter for USC (Universal Social Charge) value.
    public double getUsc() {
      return usc;
    }

    // Getter for PRSI (Pay Related Social Insurance) value.
    public double getPrsi() {
      return prsi;
    }

    // Getter for total tax value.
    public double getTotalTax() {
      return totalTax;
    }
  }

  /** Calculates various tax components based on gross income and tax credits. */
  public static TaxCalculationResult calculateTax(double grossIncome, double taxCredits) {
    double incomeTaxRate = 0.2; // Rate for income tax.
    double uscRate = 0.0106; // Rate for USC.
    double prsiRate = 0.019; // Rate for PRSI.

    // Calculates income tax, ensuring it doesn't fall below zero.
    double incomeTax = (grossIncome * incomeTaxRate) - taxCredits;
    if (incomeTax < 0) {
      incomeTax = 0;
    }

    // Calculates USC based on gross income.
    double usc = grossIncome * uscRate;

    // Calculates PRSI based on gross income.
    double prsi = grossIncome * prsiRate;

    // Sums all tax components to get the total tax.
    double totalTax = incomeTax + usc + prsi;

    // Returns a new TaxCalculationResult object containing the calculated values.
    return new TaxCalculationResult(incomeTax, usc, prsi, totalTax);
  }
}
