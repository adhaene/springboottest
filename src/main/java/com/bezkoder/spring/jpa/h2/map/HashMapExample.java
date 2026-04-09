package com.bezkoder.spring.jpa.h2.map;

// Java program demonstrating HashMap usage
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap to store student names and their grades
        HashMap<String, Integer> studentGrades = new HashMap<>();

        // Adding key-value pairs to the HashMap
        studentGrades.put("Alice", 90);
        studentGrades.put("Bob", 85);
        studentGrades.put("Charlie", 78);
        studentGrades.put("Diana", 92);

        // Accessing individual elements
        System.out.println("Alice's grade: " + studentGrades.get("Alice"));

        // Iterating through all entries of the HashMap
        System.out.println(" All student grades:");
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Checking if a specific key exists
        if (studentGrades.containsKey("Bob")) {
            System.out.println(" Bob's grade exists in HashMap.");
        }

        // Checking if a specific value exists
        if (studentGrades.containsValue(92)) {
            System.out.println("Someone has a grade of 92.");
        }

        // Removing an element by key
        studentGrades.remove("Charlie");
        System.out.println(" After removing Charlie: " + studentGrades);

        // Summary statistics using iteration
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int grade : studentGrades.values()) {
            sum += grade;
            if (grade > max) max = grade;
            if (grade < min) min = grade;
        }
        double average = sum / (double) studentGrades.size();

        System.out.println(" Summary Statistics:");
        System.out.println("Total students: " + studentGrades.size());
        System.out.println("Maximum grade: " + max);
        System.out.println("Minimum grade: " + min);
        System.out.println("Average grade: " + average);
    }
}
