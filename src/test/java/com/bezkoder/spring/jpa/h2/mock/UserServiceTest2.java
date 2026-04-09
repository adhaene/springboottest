package com.bezkoder.spring.jpa.h2.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest2 {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserService userService;

    UserService userService1 = new UserService(userRepository);
    @Test
    void test(){

    }


    @BeforeAll
    static void beforeAll(){
        //UserService userService1 = new UserService(userRepository);
    }

}