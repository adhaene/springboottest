package com.bezkoder.spring.jpa.h2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcBatchTransactionExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // Change DB name
        String user = "root"; // Change username
        String password = "password"; // Change password

        String insertSQL = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Establish connection
            conn = DriverManager.getConnection(url, user, password);

            // 2. Disable auto-commit for transaction control
            conn.setAutoCommit(false);

            // 3. Prepare statement
            pstmt = conn.prepareStatement(insertSQL);

            // 4. Add multiple records to batch
            pstmt.setString(1, "Alice");
            pstmt.setString(2, "IT");
            pstmt.setDouble(3, 60000);
            pstmt.addBatch();

            pstmt.setString(1, "Bob");
            pstmt.setString(2, "HR");
            pstmt.setDouble(3, 50000);
            pstmt.addBatch();

            pstmt.setString(1, "Charlie");
            pstmt.setString(2, "Finance");
            pstmt.setDouble(3, 70000);
            pstmt.addBatch();

            // 5. Execute batch
            int[] results = pstmt.executeBatch();

            // 6. Commit transaction if all succeed
            conn.commit();
            System.out.println("Batch executed successfully. Rows affected: " + results.length);

        } catch (SQLException e) {
            // Rollback if any error occurs
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transaction rolled back due to error: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
        } finally {
            // 7. Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                System.err.println("Error closing resources: " + closeEx.getMessage());
            }
        }
    }
}
