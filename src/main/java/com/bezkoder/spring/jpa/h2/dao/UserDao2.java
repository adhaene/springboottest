package com.bezkoder.spring.jpa.h2.dao;


import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class UserDao2 {
    private final DataSource dataSource;

    public UserDao2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTable() throws SQLException {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL)");
        }
    }

    public void insertUser(int id, String name) throws SQLException {
        String sql = "INSERT INTO users (id, name) VALUES (?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    public Optional<String> findUserNameById(int id) throws SQLException {
        String sql = "SELECT name FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getString("name"));
                }
            }
        }
        return Optional.empty();
    }
}
