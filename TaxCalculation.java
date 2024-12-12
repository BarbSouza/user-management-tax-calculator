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
    private int userId;
    private String calculationDateStr;
//    private String username;
    private double grossIncome, taxCredits, incomeTax, usc, prsi, totalTax;

    public TaxCalculation(int userId, double grossIncome, double taxCredits, double incomeTax, double usc, double prsi, double totalTax) {
        this.userId = userId;
//        this.username = username;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
        this.totalTax = totalTax;
    }
    
    public TaxCalculation(int calculationId,int userId, double grossIncome, double taxCredits, double incomeTax, double usc, double prsi, double totalTax) {
        this.calculationId = calculationId;
        this.userId = userId;
//        this.username = username;
        this.grossIncome = grossIncome;
        this.taxCredits = taxCredits;
        this.incomeTax = incomeTax;
        this.usc = usc;
        this.prsi = prsi;
        this.totalTax = totalTax;
    }
    
    public TaxCalculation(int calculationId, int userId, double grossIncome, double taxCredits, double incomeTax, double usc, double prsi, double totalTax, String calculationDateStr) {
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

    public String getCalculationDateStr() {
        return calculationDateStr;
    }

    public void setCalculationDateStr(String calculationDate) {
        this.calculationDateStr = calculationDate;
    }

    public int getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(int calculationId) {
        this.calculationId = calculationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public String getUsername() { 
//        return username; 
//    }
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
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("|------------------------------------------------------------------------------------------------------------------------------------| \n")
          .append(String.format("| %-16s | %-16s | %-16s | %-16s | %-16s | %-16s | %-16s |\n", calculationId, calculationDateStr, grossIncome, taxCredits, usc, prsi, totalTax))
          .append("|------------------------------------------------------------------------------------------------------------------------------------|\n");
        return sb.toString();
    }

}
