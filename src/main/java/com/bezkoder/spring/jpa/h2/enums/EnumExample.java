package com.bezkoder.spring.jpa.h2.enums;

// EnumExample.java
public class EnumExample {

    // Define an enum with constants, fields, and methods
    enum Day {
        MONDAY("Start of the work week"),
        TUESDAY("Second work day"),
        WEDNESDAY("Midweek"),
        THURSDAY("Almost weekend"),
        FRIDAY("Last work day"),
        SATURDAY("Weekend!"),
        SUNDAY("Rest day");

        private final String description;

        // Constructor for enum
        Day(String description) {
            this.description = description;
        }

        // Getter method
        public String getDescription() {
            return description;
        }
    }

    public static void main(String[] args) {
        // Example: iterate over all enum values
        System.out.println("All days with descriptions:");
        for (Day day : Day.values()) {
            System.out.println(day + " - " + day.getDescription());
        }

        // Example: using enum in a switch statement
        Day today = Day.FRIDAY;
        System.out.println("\nToday is: " + today);

        switch (today) {
            case MONDAY:
                System.out.println("Back to work!");
                break;
            case FRIDAY:
                System.out.println("Weekend is near!");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Enjoy your weekend!");
                break;
            default:
                System.out.println("Just another work day.");
        }
    }
}

