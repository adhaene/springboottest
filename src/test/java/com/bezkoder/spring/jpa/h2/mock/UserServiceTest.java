package com.bezkoder.spring.jpa.h2.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Mocked dependency

    @InjectMocks
    private UserService userService; // Class under test

    @BeforeEach
    void setUp() {
        // if using @ExtendWith(MockitoExtension.class) NO NEED
        //MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetUserName_UserExists() {
        // Arrange
        User mockUser = new User(1, "Alice");
        when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        // Act
        String result = userService.getUserName(1);

        // Assert
        assertEquals("Alice", result);
        verify(userRepository, times(1)).findById(1); // Verify method call
    }

    @Test
    void testGetUserName_UserNotFound() {
        // Arrange
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        String result = userService.getUserName(2);

        // Assert
        assertEquals("Unknown User", result);
        verify(userRepository, times(1)).findById(2);
    }
}