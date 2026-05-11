package com.bezkoder.spring.jpa.h2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransactionTryWithResources {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bankdb"; // Change DB name
        String user = "root"; // Change username
        String password = "password"; // Change password

        String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Disable auto-commit for transaction
            conn.setAutoCommit(false);

            try (
                    PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSQL);
                    PreparedStatement depositStmt = conn.prepareStatement(depositSQL)
            ) {
                // Withdraw from account 1
                withdrawStmt.setDouble(1, 500.00);
                withdrawStmt.setInt(2, 1);
                withdrawStmt.executeUpdate();

                // Deposit into account 2
                depositStmt.setDouble(1, 500.00);
                depositStmt.setInt(2, 2);
                depositStmt.executeUpdate();

                // Commit transaction
                conn.commit();
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                // Rollback if any statement fails
                conn.rollback();
                System.out.println("Transaction rolled back due to error.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
