package com.bezkoder.spring.jpa.h2.string;

import java.util.Scanner;

public class MaxConsecutiveChar {

    // Method to find the maximum consecutive repeating character
    public static void findMaxConsecutiveChar(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("String is empty or null.");
            return;
        }

        char maxChar = str.charAt(0);
        int maxCount = 1;

        char currentChar = str.charAt(0);
        int currentCount = 1;

        // Loop through the string starting from the second character
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == currentChar) {
                currentCount++;
            } else {
                // Reset for new character
                currentChar = str.charAt(i);
                currentCount = 1;
            }

            // Update max if needed
            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxChar = currentChar;
            }
        }

        System.out.println("Character: '" + maxChar + "' repeated " + maxCount + " times consecutively.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        findMaxConsecutiveChar(input);

        scanner.close();
    }
}
