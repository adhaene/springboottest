package com.bezkoder.spring.jpa.h2.string;

import java.util.Scanner;

public class KeepOnlyAlphabets {

    // Method to keep only alphabetic characters
    public static String filterAlphabets(String input) {
        if (input == null) {
            return "";
        }
        // Replace all non-alphabetic characters with an empty string
        return input.replaceAll("[^A-Za-z]", "");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Filter and display result
        String result = filterAlphabets(input);
        System.out.println("Only alphabets: " + result);

        scanner.close();
    }
}
