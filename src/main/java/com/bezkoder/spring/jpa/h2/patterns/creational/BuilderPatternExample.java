package com.bezkoder.spring.jpa.h2.patterns.creational;

/*
Purpose: The Builder Pattern is a creational design pattern used to construct complex objects step-by-step without having to pass all parameters to a constructor at once.
Key Benefits:
Improves readability and maintainability.
Supports method chaining for cleaner code.
Can create immutable objects.
Avoids telescoping constructors (constructors with many parameters).
Structure:
Product – The object being built.
Builder – Inner static class with methods to set fields.
Director (optional) – Controls the building process.
Client – Uses the builder to create the object.
 */
public class BuilderPatternExample {

    // Product class
    public static class User {
        private final String firstName; // required
        private final String lastName;  // required
        private final int age;          // optional
        private final String email;     // optional

        // Private constructor to enforce use of Builder
        private User(Builder builder) {
            this.firstName = builder.firstName;
            this.lastName = builder.lastName;
            this.age = builder.age;
            this.email = builder.email;
        }

        // Static inner Builder class
        public static class Builder {
            private final String firstName;
            private final String lastName;
            private int age;
            private String email;

            public Builder(String firstName, String lastName) {
                if (firstName == null || lastName == null) {
                    throw new IllegalArgumentException("First and last name are required.");
                }
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public Builder age(int age) {
                if (age < 0) throw new IllegalArgumentException("Age cannot be negative.");
                this.age = age;
                return this;
            }

            public Builder email(String email) {
                if (email != null && !email.contains("@")) {
                    throw new IllegalArgumentException("Invalid email format.");
                }
                this.email = email;
                return this;
            }

            public User build() {
                return new User(this);
            }
        }

        @Override
        public String toString() {
            return String.format("User{name='%s %s', age=%d, email='%s'}",
                    firstName, lastName, age, email);
        }
    }

    // Main method to test
    public static void main(String[] args) {
        User user = new User.Builder("John", "Doe")
                .age(30)
                .email("john.doe@example.com")
                .build();

        System.out.println(user);
    }
}
