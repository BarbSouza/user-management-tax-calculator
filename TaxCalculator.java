/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Barbara
 * @author chrystiandybas
 */
public class TaxCalculator {
    public static class TaxCalculationResult {
        private double incomeTax;
        private double usc;
        private double prsi;
        private double totalTax;

        
        public TaxCalculationResult(double incomeTax, double usc, double prsi, double totalTax) {
            this.incomeTax = incomeTax;
            this.usc = usc;
            this.prsi = prsi;
            this.totalTax = totalTax;
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

    
    public static TaxCalculationResult calculateTax(double grossIncome, double taxCredits) {
        double incomeTaxRate = 0.2; 
        double uscRate = 0.0106;  
        double prsiRate = 0.019;   
        //numbers got from my actual payslip
        //https://www.citizensinformation.ie/en/money-and-tax/tax/income-tax/how-your-tax-is-calculated/
        
        double incomeTax = (grossIncome * incomeTaxRate) - taxCredits;
        if (incomeTax < 0) incomeTax = 0; // Ensure no negative tax

        double usc = grossIncome * uscRate;
        double prsi = grossIncome * prsiRate;

        
        double totalTax = incomeTax + usc + prsi;

        
        return new TaxCalculationResult(incomeTax, usc, prsi, totalTax);
    }
    
}
