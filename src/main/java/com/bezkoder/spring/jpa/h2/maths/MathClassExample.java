package com.bezkoder.spring.jpa.h2.maths;

public class MathClassExample {
    public static void main(String[] args) {
        // Constants
        System.out.println("PI: " + Math.PI);
        System.out.println("E: " + Math.E);

        // Basic operations
        System.out.println("Absolute of -15: " + Math.abs(-15));
        System.out.println("Max of 10 and 20: " + Math.max(10, 20));
        System.out.println("Min of 10 and 20: " + Math.min(10, 20));

        // Power and roots
        System.out.println("2^5: " + Math.pow(2, 5));
        System.out.println("Square root of 81: " + Math.sqrt(81));

        // Rounding
        System.out.println("Round 5.7: " + Math.round(5.7));
        System.out.println("Ceil 5.2: " + Math.ceil(5.2));
        System.out.println("Floor 5.8: " + Math.floor(5.8));

        // Trigonometry
        double degrees = 45;
        double radians = Math.toRadians(degrees);
        System.out.println("Sin 45°: " + Math.sin(radians));

        // Random number
        System.out.println("Random number (0-1): " + Math.random());
    }
}
