package com.bezkoder.spring.jpa.h2.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/*
http://localhost:8080/swagger-ui.html
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    @Operation(
            summary = "Get user by ID",
            description = "Fetches a user from the database using their unique ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class),
                                    examples = @ExampleObject(
                                            name = "UserExample",
                                            value = "{ \"id\": 1, \"name\": \"John Doe\", \"email\": \"john@example.com\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    public User getUser(@PathVariable Long id) {
        return new User(id, "John Doe", "john@example.com");
    }
}
