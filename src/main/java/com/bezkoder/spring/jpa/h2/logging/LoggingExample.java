package com.bezkoder.spring.jpa.h2.logging;

import java.util.logging.*;

/*
Purpose: Logging is used to record messages for debugging, monitoring, and auditing.
Levels: Common log levels include SEVERE, WARNING, INFO, CONFIG, FINE, FINER, and FINEST.
Components:
Logger: Creates and manages log messages.
Handler: Sends log messages to destinations (console, file, etc.).
Formatter: Formats log messages.
Advantages over System.out.println():
Configurable output levels.
Can log to multiple destinations.
Can be turned on/off without changing code.
Example: Basic Java Logging
Java

How it works
Logger Creation: Logger.getLogger() creates a logger instance.
Handler: FileHandler writes logs to app.log.
By default, logs also go to the console.
Formatter: SimpleFormatter formats log messages in a readable way.
Levels: You can filter logs by setting logger.setLevel(Level.X).
Exception Logging: logger.log(Level.SEVERE, "message", exception) logs stack traces.

 */
public class LoggingExample {
    private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());

    public static void main(String[] args) {
        try {
            // Optional: Configure logger to log to a file
            FileHandler fileHandler = new FileHandler("app.log", true); // append mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Set logging level
            logger.setLevel(Level.ALL);

            // Log messages at different levels
            logger.severe("Severe message - critical error");
            logger.warning("Warning message - potential issue");
            logger.info("Info message - general information");
            logger.config("Config message - configuration details");
            logger.fine("Fine message - debug details");

            // Simulate an exception
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                logger.log(Level.SEVERE, "Exception occurred", e);
            }

        } catch (Exception e) {
            System.err.println("Logging setup failed: " + e.getMessage());
        }
    }
}
