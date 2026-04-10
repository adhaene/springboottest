package com.bezkoder.spring.jpa.h2.controller;

import io.swagger.v3.oas.annotations.media.Schema;

public class User {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "John Doe")
    private String name;

    @Schema(example = "john@example.com")
    private String email;

    // Constructors, getters, setters
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
