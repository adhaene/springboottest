package com.bezkoder.spring.jpa.h2.exception;

// Custom checked exception class
class InvalidAgeException extends Exception {
    // Constructor with message
    public InvalidAgeException(String message) {
        super(message);
    }
}

//class InvalidAgeRunTimeException extends RuntimeException {
//    // Constructor with message
//    public InvalidAgeRunTimeException(String message) {
//        super(message);
//    }
//}

public class CheckedExceptionDemo {

    // Method that throws the custom checked exception
    public static void registerUser(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("User must be at least 18 years old.");
        }
        System.out.println("User registered successfully. Age: " + age);
    }

    public static void main(String[] args) {
        try {
            // Example with invalid age
            registerUser(15);
        } catch (InvalidAgeException e) {
            // Handle the checked exception
            System.err.println("Registration failed: " + e.getMessage());
        }

        try {
            // Example with valid age
            registerUser(25);
        } catch (InvalidAgeException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
    }
}

