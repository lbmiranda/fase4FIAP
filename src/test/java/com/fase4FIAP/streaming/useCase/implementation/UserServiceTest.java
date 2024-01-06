package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.domain.repository.UserRepository;
import com.fase4FIAP.streaming.useCase.contract.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class UserServiceTest {

    final String id = "c1229ad8-e869-4cf0-bf1b-de193465248b";

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

    // TODO - Ainda pendente
    @Test
    void allowSaveUser(){
        var user = new UserRequest("USER", "contato@hotmail.com", "123");
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        var userResponse = userService.create(user);
        var userCreated = User.ofResponse(userResponse);

        assertThat(userCreated).isInstanceOf(User.class).isNotNull();
        assertThat(userCreated.getName()).isEqualTo(user.getName());
        assertThat(userCreated.getEmail()).isEqualTo(user.getEmail());
        assertThat(userCreated.getPassword()).isEqualTo(user.getPassword());
        assertThat(userCreated.getPassword()).isNotNull();

    }


}
