package com.bezkoder.spring.jpa.h2.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input two integers
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            // Division operation
            int result = divide(num1, num2);
            System.out.println("Result: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid integers.");
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            // Always executes
            System.out.println("Program finished.");
            scanner.close();
        }
    }

    // Method that declares it may throw ArithmeticException
    public static int divide(int a, int b) throws ArithmeticException {
        return a / b; // May throw ArithmeticException if b == 0
    }
}
