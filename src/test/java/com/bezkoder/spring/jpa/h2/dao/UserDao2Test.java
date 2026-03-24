package com.bezkoder.spring.jpa.h2.dao;


import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDao2Test {

    private UserDao2 userDao;

    @BeforeAll
    void setupDatabase() throws SQLException {
        // Configure H2 in-memory DataSource
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        userDao = new UserDao2(dataSource);
        userDao.createTable();
    }

    @Test
    void testInsertAndFindUser() throws SQLException {
        userDao.insertUser(1, "Alice");
        Optional<String> name = userDao.findUserNameById(1);

        assertTrue(name.isPresent(), "User should be found");
        assertEquals("Alice", name.get());
    }

    @Test
    void testFindNonExistingUser() throws SQLException {
        Optional<String> name = userDao.findUserNameById(999);
        assertFalse(name.isPresent(), "User should not be found");
    }
}
