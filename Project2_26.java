package com.mycompany.project2_26;

import java.util.Scanner;
import java.util.InputMismatchException;

class PositiveRealNumber {
    private int integerPart;      // integer part
    private double fractionalPart; // fractional part (0 <= fractionalPart < 1)
    
    public PositiveRealNumber() {
        this.integerPart = 0;
        this.fractionalPart = 0.0;
    }
   
    public void init(int integerPart, double fractionalPart) {
        if (integerPart < 0 || fractionalPart < 0 || fractionalPart >= 1) {
            throw new IllegalArgumentException("Invalid data: integer part must be >= 0, fractional part must be in range [0, 1)");
        }
        this.integerPart = integerPart;
        this.fractionalPart = fractionalPart;
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        
        // Input integer part
        boolean validIntegerInput = false;
        while (!validIntegerInput) {
            System.out.println("Enter integer part (non-negative integer):");
            try {
                int input = scanner.nextInt();
                if (input >= 0) {
                    this.integerPart = input;
                    validIntegerInput = true;
                } else {
                    System.out.println("Error: Integer part must be non-negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter an integer.");
                scanner.next(); // Clear scanner buffer
            }
        }
        
        // Input fractional part
        boolean validFractionalInput = false;
        while (!validFractionalInput) {
            System.out.println("Enter fractional part (real number in range [0, 1)):");
            try {
                double input = scanner.nextDouble();
                if (input >= 0 && input < 1) {
                    this.fractionalPart = input;
                    validFractionalInput = true;
                } else {
                    System.out.println("Error: Fractional part must be in range [0, 1).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a real number.");
                scanner.next(); // Clear scanner buffer
            }
        }
    }

    public void display() {
        System.out.println("(" + this.integerPart + "." + this.fractionalPart + ")");
    }
    
    public int round() {
        int rounded = this.integerPart;
        if(this.fractionalPart >= 0.5)
            rounded += 1;
        return rounded;
    }
    
    public static PositiveRealNumber add(PositiveRealNumber num1, PositiveRealNumber num2) {
        double sum = (num1.integerPart + num2.integerPart) + (num1.fractionalPart + num2.fractionalPart);

        int newIntegerPart = (int) sum;
        double newFractionalPart = sum - newIntegerPart;
        
        PositiveRealNumber res = new PositiveRealNumber();
        res.init(newIntegerPart, newFractionalPart);
        return res;
    }

    public int getIntegerPart() {
        return integerPart;
    }
    
    public void setIntegerPart(int integerPart) {
        if (integerPart < 0) {
            throw new IllegalArgumentException("Integer part must be non-negative");
        }
        this.integerPart = integerPart;
    }
    
    public double getFractionalPart() {
        return fractionalPart;
    }
    
    public void setFractionalPart(double fractionalPart) {
        if (fractionalPart < 0 || fractionalPart >= 1) {
            throw new IllegalArgumentException("Fractional part must be in range [0, 1)");
        }
        this.fractionalPart = fractionalPart;
    }
    
    // Get full number as double
    public double toDouble() {
        return this.integerPart + this.fractionalPart;
    }
}

// Demonstration class
public class Project2_26 {
    public static void main(String[] args) {
        // Create and test numbers
        PositiveRealNumber num1 = new PositiveRealNumber();
        num1.init(3, 0.7);
        PositiveRealNumber num2 = new PositiveRealNumber();
        num2.init(4, 0.4);
        
        System.out.println("First number:");
        num1.display();
        
        System.out.println("Second number:");
        num2.display();

        System.out.println("First number rounded: " + num1.round());
        System.out.println("Second number rounded: " + num2.round());

        PositiveRealNumber sum = PositiveRealNumber.add(num1, num2);
        System.out.println("Sum of numbers:");
        sum.display();

        System.out.println("\n=== Enter new number from keyboard ===");
        PositiveRealNumber num3 = new PositiveRealNumber();
        num3.read();
        
        System.out.println("Entered number:");
        num3.display();
        System.out.println("Rounded value: " + num3.round());

        PositiveRealNumber sum2 = PositiveRealNumber.add(num1, num3);
        System.out.println("Sum of first and entered number:");
        sum2.display();
    }
}
