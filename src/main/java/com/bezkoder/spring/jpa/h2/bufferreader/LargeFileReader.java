package com.bezkoder.spring.jpa.h2.bufferreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LargeFileReader {

    public static void main(String[] args) {
        // Example file path - change this to your actual file
        String filePath = "C:\\Users\\arnau\\workspace\\spring-boot-h2-database-crud-master\\src\\main\\resources\\largefile.txt";
        long wordCount = 0;
        // Use try-with-resources to ensure the file is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long lineCount = 0;

            // Read file line-by-line
            while ((line = br.readLine()) != null) {
                lineCount++;
                // Process the line here
                // For demonstration, we'll just print the first 5 lines
                if (lineCount <= 5) {
                    System.out.println(line);
                }
                wordCount += line.split(" ").length;
            }
            System.out.println("Total wordCount read: " + wordCount);

            System.out.println("Total lines read: " + lineCount);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
