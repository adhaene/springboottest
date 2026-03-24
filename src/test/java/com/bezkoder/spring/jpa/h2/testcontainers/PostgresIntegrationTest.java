package com.bezkoder.spring.jpa.h2.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PostgresIntegrationTest {
    @Test
    void testPostgresWithContainer() throws Exception {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")) {
            postgres.start();
            try (Connection conn = DriverManager.getConnection(
                    postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())) {
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE demo(id SERIAL PRIMARY KEY, name VARCHAR(255))");
                stmt.execute("INSERT INTO demo(name) VALUES ('Testcontainers')");
                ResultSet rs = stmt.executeQuery("SELECT name FROM demo WHERE id=1");
                rs.next();
                assertEquals("Testcontainers", rs.getString("name"));
            }
        }
    }
}