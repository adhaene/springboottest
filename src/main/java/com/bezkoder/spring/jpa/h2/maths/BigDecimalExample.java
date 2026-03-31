package com.bezkoder.spring.jpa.h2.maths;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalExample {

    public static void main(String[] args) {
        try {
            // Creating BigDecimal from String (recommended for precision)
            BigDecimal num1 = new BigDecimal("10.25");
            BigDecimal num2 = new BigDecimal("3.5");

            // Addition
            BigDecimal sum = num1.add(num2);
            System.out.println("Sum: " + sum); // 13.75

            // Subtraction
            BigDecimal difference = num1.subtract(num2);
            System.out.println("Difference: " + difference); // 6.75

            // Multiplication
            BigDecimal product = num1.multiply(num2);
            System.out.println("Product: " + product); // 35.875

            // Division with scale and rounding
            BigDecimal quotient = num1.divide(num2, 4, RoundingMode.HALF_UP);
            System.out.println("Quotient (4 decimal places): " + quotient); // 2.9286

            // Comparison
            int comparison = num1.compareTo(num2);
            if (comparison > 0) {
                System.out.println(num1 + " is greater than " + num2);
            } else if (comparison < 0) {
                System.out.println(num1 + " is less than " + num2);
            } else {
                System.out.println(num1 + " is equal to " + num2);
            }

            // Setting scale (rounding to 2 decimal places)
            BigDecimal rounded = product.setScale(2, RoundingMode.HALF_UP);
            System.out.println("Rounded product: " + rounded);

        } catch (ArithmeticException e) {
            System.err.println("Error in arithmetic operation: " + e.getMessage());
        }
    }
}
