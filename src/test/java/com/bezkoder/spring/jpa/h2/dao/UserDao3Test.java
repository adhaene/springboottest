package com.bezkoder.spring.jpa.h2.dao;

import com.bezkoder.spring.jpa.h2.model.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDao3Test {

    private HikariDataSource dataSource;
    private UserDaoImpl userDao;

    @BeforeAll
    void setup() {
        // Configure HikariCP for H2 in-memory DB
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        config.setUsername("sa");
        config.setPassword("");
        config.setMaximumPoolSize(5);

        dataSource = new HikariDataSource(config);
        userDao = new UserDaoImpl(dataSource);
    }

    @BeforeEach
    void initDb() throws SQLException {
        userDao.createTable();
    }

    @Test
    void testInsertAndRetrieveUsers() throws SQLException {
        userDao.insertUser(new User(1, "Alice"));
        userDao.insertUser(new User(2, "Bob"));

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("Alice")));
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("Bob")));
    }

    @AfterAll
    void tearDown() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
