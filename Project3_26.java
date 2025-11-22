package com.mycompany.project3_26;
import java.util.Scanner;
import java.util.InputMismatchException;

class Culture {
    private double fertilizerCostPerHectare; // fertilizer cost per hectare
    private double incomePerHectare; // selling price per hectare (income)

    public Culture() {
        this.fertilizerCostPerHectare = 0.0;
        this.incomePerHectare = 0.0;
    }

    public void init(double fertilizerCost, double income) {
        this.fertilizerCostPerHectare = fertilizerCost;
        this.incomePerHectare = income;
    }

    public void read() 
    {
        Scanner scanner = new Scanner(System.in);

        boolean validFertilizerInput = false;
        while (!validFertilizerInput) {
            System.out.println("Enter fertilizer cost per hectare:");
            try {
                this.fertilizerCostPerHectare = scanner.nextDouble();
                validFertilizerInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a real number.");
                scanner.next(); // Clear scanner buffer
            }
        }

        boolean validIncomeInput = false;
        while (!validIncomeInput) {
            System.out.println("Enter selling price per hectare (income):");
            try {
                this.incomePerHectare = scanner.nextDouble();
                validIncomeInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a real number.");
                scanner.next(); // Clear scanner buffer
            }
        }
    }

    public void display() {
        System.out.println("Fertilizer cost per hectare: " + this.fertilizerCostPerHectare);
        System.out.println("Income per hectare: " + this.incomePerHectare);
        System.out.println("Profit per hectare: " + calculateProfitPerHectare());
    }

    public double calculateProfitPerHectare() {
        return this.incomePerHectare - this.fertilizerCostPerHectare;
    }

    public double getFertilizerCostPerHectare() {
        return fertilizerCostPerHectare;
    }

    public void setFertilizerCostPerHectare(double fertilizerCostPerHectare) {
        this.fertilizerCostPerHectare = fertilizerCostPerHectare;
    }

    public double getIncomePerHectare() {
        return incomePerHectare;
    }

    public void setIncomePerHectare(double incomePerHectare) {
        this.incomePerHectare = incomePerHectare;
    }
}

class Village {
    private String name;
    private Culture culture1;
    private Culture culture2;
    private Culture culture3;
    private int tonsOfCulture1; // tons of planted crops
    private int tonsOfCulture2;
    private int tonsOfCulture3;
    private double additionalProfit; // profit from additional economic activities

    public Village() {
        this.name = "";
        this.culture1 = new Culture();
        this.culture2 = new Culture();
        this.culture3 = new Culture();
        this.tonsOfCulture1 = 0;
        this.tonsOfCulture2 = 0;
        this.tonsOfCulture3 = 0;
        this.additionalProfit = 0.0;
    }

    public void init(String name, Culture culture1, Culture culture2, Culture culture3, 
                    int tons1, int tons2, int tons3, double additionalProfit) {
        this.name = name;
        this.culture1 = culture1;
        this.culture2 = culture2;
        this.culture3 = culture3;
        this.tonsOfCulture1 = tons1;
        this.tonsOfCulture2 = tons2;
        this.tonsOfCulture3 = tons3;
        this.additionalProfit = additionalProfit;
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Enter name for village ===");
        name = scanner.next();
        
        System.out.println("=== Enter data for cultures ===");
        System.out.println("Culture 1:");
        this.culture1.read();
        
        System.out.println("Culture 2:");
        this.culture2.read();
        
        System.out.println("Culture 3:");
        this.culture3.read();

        System.out.println("=== Enter tons of planted crops ===");
        this.tonsOfCulture1 = readIntInput("Enter tons for culture 1:");
        this.tonsOfCulture2 = readIntInput("Enter tons for culture 2:");
        this.tonsOfCulture3 = readIntInput("Enter tons for culture 3:");

        boolean validProfitInput = false;
        while (!validProfitInput) {
            System.out.println("Enter profit from additional economic activities:");
            try {
                this.additionalProfit = scanner.nextDouble();
                validProfitInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a real number.");
                scanner.next(); // Clear scanner buffer
            }
        }
    }

    private int readIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int value = 0;
        
        while (!validInput) {
            System.out.println(message);
            try {
                value = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
                scanner.next(); // Clear scanner buffer
            }
        }
        return value;
    }

    public void display() {
        System.out.println("=== Village Information ===");
        System.out.println("Village name: " + this.name + "\n");
        System.out.println("Culture 1:");
        this.culture1.display();
        System.out.println("Tons of culture 1: " + this.tonsOfCulture1);
        System.out.println("Profit from culture 1: " + calculateCultureProfit(1));
        
        System.out.println("\nCulture 2:");
        this.culture2.display();
        System.out.println("Tons of culture 2: " + this.tonsOfCulture2);
        System.out.println("Profit from culture 2: " + calculateCultureProfit(2));
        
        System.out.println("\nCulture 3:");
        this.culture3.display();
        System.out.println("Tons of culture 3: " + this.tonsOfCulture3);
        System.out.println("Profit from culture 3: " + calculateCultureProfit(3));
        
        System.out.println("\nAdditional profit: " + this.additionalProfit);
        System.out.println("Total village profit: " + calculateTotalProfit());
        
        findMostProfitableCulture();
    }

    public double calculateCultureProfit(int cultureNumber) {
        double profitPerHectare;
        int tons;
        
        switch (cultureNumber) {
            case 1:
                profitPerHectare = culture1.calculateProfitPerHectare();
                tons = tonsOfCulture1;
                break;
            case 2:
                profitPerHectare = culture2.calculateProfitPerHectare();
                tons = tonsOfCulture2;
                break;
            case 3:
                profitPerHectare = culture3.calculateProfitPerHectare();
                tons = tonsOfCulture3;
                break;
            default:
                System.out.println("Invalid culture number");
                return 0.0;
        }
        
        return profitPerHectare * tons;
    }

    public double calculateTotalProfit() {
        double totalCultureProfit = calculateCultureProfit(1) + calculateCultureProfit(2) + calculateCultureProfit(3);
        return totalCultureProfit + additionalProfit;
    }

    public void findMostProfitableCulture() {
        double profit1 = calculateCultureProfit(1);
        double profit2 = calculateCultureProfit(2);
        double profit3 = calculateCultureProfit(3);
        
        double maxProfit = Math.max(profit1, Math.max(profit2, profit3));
        
        System.out.println("\n=== Culture Profitability Analysis ===");
        if (maxProfit == profit1) {
            System.out.println("Culture 1 is the most profitable: " + profit1);
        }
        if (maxProfit == profit2) {
            System.out.println("Culture 2 is the most profitable: " + profit2);
        }
        if (maxProfit == profit3) {
            System.out.println("Culture 3 is the most profitable: " + profit3);
        }
    }

    public Culture getCulture1() {
        return culture1;
    }

    public void setCulture1(Culture culture1) {
        this.culture1 = culture1;
    }

    public Culture getCulture2() {
        return culture2;
    }

    public void setCulture2(Culture culture2) {
        this.culture2 = culture2;
    }

    public Culture getCulture3() {
        return culture3;
    }

    public void setCulture3(Culture culture3) {
        this.culture3 = culture3;
    }

    public int getTonsOfCulture1() {
        return tonsOfCulture1;
    }

    public void setTonsOfCulture1(int tonsOfCulture1) {
        this.tonsOfCulture1 = tonsOfCulture1;
    }

    public int getTonsOfCulture2() {
        return tonsOfCulture2;
    }

    public void setTonsOfCulture2(int tonsOfCulture2) {
        this.tonsOfCulture2 = tonsOfCulture2;
    }

    public int getTonsOfCulture3() {
        return tonsOfCulture3;
    }

    public void setTonsOfCulture3(int tonsOfCulture3) {
        this.tonsOfCulture3 = tonsOfCulture3;
    }

    public double getAdditionalProfit() {
        return additionalProfit;
    }

    public void setAdditionalProfit(double additionalProfit) {
        this.additionalProfit = additionalProfit;
    }
}

public class Project3_26 {
    public static void main(String[] args) {
        Village village = new Village();

        Culture culture1 = new Culture();
        culture1.init(2500.0, 6220.0);
        
        Culture culture2 = new Culture();
        culture2.init(1120.0, 2180.0); 
        
        Culture culture3 = new Culture();
        culture3.init(4390.0, 3890.0); 
        
        village.init("Buranovo", culture1, culture2, culture3, 12, 11, 4, 4568.0);
        village.display();

        System.out.println("\n=== Enter data from keyboard ===");
        Village village2 = new Village();
        village2.read();
        village2.display();
    }
}
