package com.bezkoder.spring.jpa.h2.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackExample {

    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(LogbackExample.class);

    public static void main(String[] args) {
        logger.trace("This is a TRACE level message");
        logger.debug("This is a DEBUG level message");
        logger.info("This is an INFO level message");
        logger.warn("This is a WARN level message");
        logger.error("This is an ERROR level message");

        try {
            simulateError();
        } catch (Exception e) {
            logger.error("An exception occurred", e);
        }
    }

    private static void simulateError() throws Exception {
        throw new Exception("Simulated exception for logging");
    }
}

