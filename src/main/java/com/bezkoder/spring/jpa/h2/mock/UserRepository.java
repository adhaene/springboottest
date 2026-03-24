package com.bezkoder.spring.jpa.h2.mock;

import java.util.Optional;

interface UserRepository {
    Optional<User> findById(int id);
}