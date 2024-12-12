/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 * @author chrystiandybas
 */
public class TaxCalculation {
    private int calculationId;
    private String username;
    private double grossIncome, taxCredits, incomeTax, usc, prsi, totalTax;

    public TaxCalculation(String username, double grossIncome, double taxCredits, double incomeTax, double usc, double prsi, double totalTax) {
        this.username = username;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
        this.totalTax = totalTax;
    }
    
    public TaxCalculation(int calculationId,String username, double grossIncome, double taxCredits, double incomeTax, double usc, double prsi, double totalTax) {
        this.calculationId = calculationId;
        this.username = username;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
        this.totalTax = totalTax;
    }

    public int getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(int calculationId) {
        this.calculationId = calculationId;
    }

    public String getUsername() { 
        return username; 
    }
    public double getGrossIncome() { 
        return grossIncome; 
    }
    public double getTaxCredits() { 
        return taxCredits; 
    }
    public double getIncomeTax() { 
        return incomeTax; 
    }
    public double getUsc() { 
        return usc; 
    }
    public double getPrsi() { 
        return prsi; 
    }
    public double getTotalTax() { 
        return totalTax; 
    }
    
}
