package com.bezkoder.spring.jpa.h2.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReadWriteExample {

    private static final String CONFIG_FILE = "config.properties";

    public static void main(String[] args) {
        Properties props = new Properties();

        // Step 1: Load existing properties
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            System.out.println("Loaded properties from file.");
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
            return;
        }

        // Step 2: Read values
        String appName = props.getProperty("app.name", "Default App");
        String appVersion = props.getProperty("app.version", "0.0.0");
        int maxUsers;
        try {
            maxUsers = Integer.parseInt(props.getProperty("max.users", "10"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format for max.users, using default 10");
            maxUsers = 10;
        }

        System.out.println("Application Name: " + appName);
        System.out.println("Version: " + appVersion);
        System.out.println("Max Users Allowed: " + maxUsers);

        // Step 3: Modify some values
        props.setProperty("app.version", "1.1.0"); // update version
        props.setProperty("last.updated", String.valueOf(System.currentTimeMillis())); // add new key

        // Step 4: Save updated properties back to file
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "Updated application configuration");
            System.out.println("Properties updated and saved to file.");
        } catch (IOException e) {
            System.err.println("Error writing properties file: " + e.getMessage());
        }
    }
}
