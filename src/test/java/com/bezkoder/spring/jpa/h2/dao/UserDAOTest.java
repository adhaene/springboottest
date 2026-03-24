package com.bezkoder.spring.jpa.h2.dao;

// UserDAOTest.java
import com.bezkoder.spring.jpa.h2.model.User;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private static Connection connection;
    private UserDAO userDAO;

    @BeforeAll
    static void setupDatabase() throws SQLException {
        // Create in-memory H2 database
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
        }
    }

    @BeforeEach
    void init() {
        userDAO = new UserDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
        }
    }

    @AfterAll
    static void closeDatabase() throws SQLException {
        connection.close();
    }

    @Test
    void testSaveAndFindAll() throws SQLException {
        User user = new User(1, "Alice");
        userDAO.save(user);

        List<User> users = userDAO.findAll();
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName());
    }

    @Test
    void testEmptyDatabase() throws SQLException {
        List<User> users = userDAO.findAll();
        assertTrue(users.isEmpty());
    }
}
