package com.bezkoder.spring.jpa.h2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankdb"; // Change DB name
        String user = "root"; // Change username
        String password = "password"; // Change password

        Connection conn = null;
        PreparedStatement withdrawStmt = null;
        PreparedStatement depositStmt = null;

        try {
            // 1. Connect to database
            conn = DriverManager.getConnection(url, user, password);

            // 2. Disable auto-commit for transaction
            conn.setAutoCommit(false);

            // 3. Prepare SQL statements
            String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

            withdrawStmt = conn.prepareStatement(withdrawSQL);
            depositStmt = conn.prepareStatement(depositSQL);

            // Withdraw from account 1
            withdrawStmt.setDouble(1, 500.00);
            withdrawStmt.setInt(2, 1);
            withdrawStmt.executeUpdate();

            // Deposit into account 2
            depositStmt.setDouble(1, 500.00);
            depositStmt.setInt(2, 2);
            depositStmt.executeUpdate();

            // 4. Commit transaction
            conn.commit();
            System.out.println("Transaction committed successfully.");

        } catch (SQLException e) {
            // 5. Rollback if any error occurs
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 6. Close resources
            try {
                if (withdrawStmt != null) withdrawStmt.close();
                if (depositStmt != null) depositStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}
