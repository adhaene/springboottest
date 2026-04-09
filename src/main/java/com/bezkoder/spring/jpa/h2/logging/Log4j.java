package com.bezkoder.spring.jpa.h2.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j {
    private static final Logger logger = LoggerFactory.getLogger(Log4j.class);

    public static void main(String[] args) {
        logger.info("Application started");
        logger.warn("Low disk space");
        logger.error("An error occurred", new RuntimeException("Test"));
    }
}
