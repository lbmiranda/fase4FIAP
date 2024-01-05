package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.repository.UserRepository;
import com.fase4FIAP.streaming.useCase.contract.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {

    private IUserService userService;

    @Mock
    private UserRepository userRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    
}
