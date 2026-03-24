package com.bezkoder.spring.jpa.h2.mock;

class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String getUserName(int id) {
        return repository.findById(id)
                .map(User::getName)
                .orElse("Unknown User");
    }
}