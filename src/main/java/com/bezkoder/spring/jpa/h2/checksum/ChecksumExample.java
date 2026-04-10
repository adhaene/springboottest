package com.bezkoder.spring.jpa.h2.checksum;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/*
What is a checksum?
A checksum is a small-sized data value calculated from a larger block of data to detect errors during transmission or storage.
It’s widely used in network protocols and file integrity checks.

Typical Java Implementation Steps
Read the data (string, file, or byte array).
Convert the data into bytes.
Sum the byte values (or use bitwise operations depending on the checksum algorithm).
Apply modulus (often % 256) to keep the checksum within a byte range.
Compare the calculated checksum with the transmitted/stored one to verify integrity.
 */
/*
Simple checksum: Fast but not cryptographically secure.
Alternatives: Use java.util.zip.CRC32 or MessageDigest (MD5, SHA) for stronger integrity checks.
Use case: Detect accidental errors, not malicious tampering.
 */
public class ChecksumExample {
    public static int generateChecksum(String data) {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        int sum = 0;
        for (byte b : bytes) {
            sum += (b & 0xFF); // Ensure unsigned addition
        }
        return sum % 256; // Keep checksum in 1 byte
    }

    public static void main(String[] args) {
        String message = "Hello World";
        int checksum = generateChecksum(message);
        System.out.println("Message: " + message);
        System.out.println("Checksum: " + checksum);

        String filePath = "C:\\Users\\arnau\\workspace\\spring-boot-h2-database-crud-master\\src\\main\\resources\\test.txt"; // Change to your file path

        try {
            long checksum2 = getCRC32Checksum(filePath);
            System.out.println("File: " + filePath);
            System.out.println("CRC32 Checksum: " + checksum2);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    // Method to calculate CRC32 checksum of a file
    public static long getCRC32Checksum(String filePath) throws IOException {
        CRC32 crc = new CRC32();
        byte[] buffer = new byte[4096]; // 4KB buffer for efficiency
        int bytesRead;

        try (FileInputStream fis = new FileInputStream(filePath)) {
            while ((bytesRead = fis.read(buffer)) != -1) {
                crc.update(buffer, 0, bytesRead);
            }
        }

        return crc.getValue();
    }
}
