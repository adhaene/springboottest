package com.bezkoder.spring.jpa.h2.array;

import java.util.Scanner;
/*
Array Declaration: int[] numbers = new int[size];
Initialization: User inputs values stored in the array.
Accessing Elements: Uses both for loop and enhanced for-each loop.
Validation: Checks for invalid size and non-integer inputs.
Example Operation: Calculates the sum of all elements.
 */
public class ArrayExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Ask user for array size
            System.out.print("Enter the number of elements: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }
            int size = scanner.nextInt();

            if (size <= 0) {
                System.out.println("Array size must be greater than zero.");
                return;
            }

            // Declare and initialize array
            int[] numbers = new int[size];

            // Input array elements
            System.out.println("Enter " + size + " integers:");
            for (int i = 0; i < size; i++) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers only.");
                    return;
                }
                numbers[i] = scanner.nextInt();
            }

            // Display array elements
            System.out.println("You entered:");
            for (int num : numbers) {
                System.out.print(num + " ");
            }
            System.out.println();

            // Example: Calculate sum of elements
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            System.out.println("Sum of elements: " + sum);

        } finally {
            scanner.close();
        }
    }
}
