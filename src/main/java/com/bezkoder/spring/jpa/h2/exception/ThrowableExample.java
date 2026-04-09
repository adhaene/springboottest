package com.bezkoder.spring.jpa.h2.exception;

import java.io.IOException;

public class ThrowableExample {

    // Method that throws a checked exception
    public static void checkedExceptionMethod() throws IOException {
        throw new IOException("Simulated checked exception");
    }

    // Method that throws an unchecked exception
    public static void uncheckedExceptionMethod() {
        throw new IllegalArgumentException("Simulated unchecked exception");
    }

    public static void main(String[] args) {
        // Example 1: Handling a checked exception
        try {
            checkedExceptionMethod();
        } catch (IOException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
            System.out.println("Stack trace:");
            e.printStackTrace();
        }

        // Example 2: Handling an unchecked exception
        try {
            uncheckedExceptionMethod();
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught unchecked exception: " + e.getMessage());
        }

        // Example 3: Exception chaining
        try {
            try {
                throw new IOException("Low-level I/O error");
            } catch (IOException e) {
                throw new RuntimeException("High-level operation failed", e);
            }
        } catch (RuntimeException e) {
            System.out.println("\nCaught chained exception: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
        }

        // Example 4: Suppressed exceptions (try-with-resources)
        try (AutoCloseableResource res = new AutoCloseableResource()) {
            throw new Exception("Main exception in try block");
        } catch (Exception e) {
            System.out.println("\nCaught exception with suppressed:");
            System.out.println("Main: " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.out.println("Suppressed: " + suppressed.getMessage());
            }
        }
    }
}

// Custom AutoCloseable resource to demonstrate suppressed exceptions
class AutoCloseableResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new Exception("Exception in close()");
    }
}
