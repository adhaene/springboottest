package com.bezkoder.spring.jpa.h2.record;


/*
Here’s a complete Java example showing how to use a record to create an immutable data class
and generate a summary from its fields.

Java records (introduced in Java 14, stable in Java 16) automatically generate:

private final fields
A canonical constructor
equals(), hashCode(), and toString() methods

How It Works
Record Declaration
record Person(String firstName, String lastName, int age)
Automatically creates:

private final String firstName;
private final String lastName;
private final int age;
Constructor, getters, equals(), hashCode(), toString()
Validation
The compact constructor checks for invalid data before assigning fields.

Custom Method
summary() builds a human-readable description.
 */
// File: PersonRecordExample.java
public class PersonRecordExample {

    // Define a record with three components
    record Person(String firstName, String lastName, int age) {

        // Compact constructor for validation
        public Person {
            if (firstName == null || firstName.isBlank()) {
                throw new IllegalArgumentException("First name cannot be null or blank");
            }
            if (lastName == null || lastName.isBlank()) {
                throw new IllegalArgumentException("Last name cannot be null or blank");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }

        // Custom method to return a summary
        public String summary() {
            return String.format("%s %s is %d years old.", firstName, lastName, age);
        }
    }

    public static void main(String[] args) {
        try {
            // Create a Person record instance
            Person person = new Person("Alice", "Johnson", 30);

            // Print the auto-generated toString()
            System.out.println("Record toString(): " + person);

            // Print the custom summary
            System.out.println("Summary: " + person.summary());

        } catch (IllegalArgumentException e) {
            System.err.println("Error creating Person: " + e.getMessage());
        }
    }
}

