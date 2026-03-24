package com.bezkoder.spring.jpa.h2.dao;

import com.bezkoder.spring.jpa.h2.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao3 {
    void createTable() throws SQLException;
    void insertUser(User user) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
