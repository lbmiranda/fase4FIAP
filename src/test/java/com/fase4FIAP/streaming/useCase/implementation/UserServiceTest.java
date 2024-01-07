package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.domain.repository.UserRepository;
import com.fase4FIAP.streaming.useCase.contract.IUserService;
import com.fase4FIAP.streaming.utils.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    final String id = "c1229ad8-e869-4cf0-bf1b-de193465248b";

    private IUserService userService;

    @Mock
    private UserRepository userRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup() {
        mock = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void allowSaveUser() {
        var user = new UserRequest("USER", "contato@hotmail.com", "123");
        Mockito.when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        var userCreated = User.ofResponse(userService.create(user));

        assertThat(userCreated).isInstanceOf(User.class).isNotNull();
        assertThat(userCreated.getName()).isEqualTo(user.getName());
        assertThat(userCreated.getEmail()).isEqualTo(user.getEmail());
        assertThat(userCreated.getPassword()).isEqualTo(user.getPassword());
        verify(userRepository, times(1)).save(userCreated);
    }

    @Test
    void allowGetAllUsers() {
        var user1 = UserHelper.createUser();
        var user2 = UserHelper.createUser();
        var user3 = UserHelper.createUser();
        var listUsers = Arrays.asList(user1, user2, user3);
        when(userRepository.findAll()).thenReturn(listUsers);

        var listFilter = userService.getAll();

        assertThat(listFilter).hasSize(3);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void allowGetByIdUser() {
        var user = UserHelper.createUser();
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(user));

        var userFilter = userService.getById(id);
        assertThat(userFilter).isEqualTo(UserResponse.of(user));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void generateNotFoundExceptionGetByIdUser() {
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Usuario não encontrado com o id: " + id);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void allowUpdateUser() {
        var userOld = new UserRequest("USER", "contato@hotmail.com", "123");

        var user = new User();
        user.setId(id);
        user.setName(userOld.getName());
        user.setEmail("novoemail@gmail.com");
        user.setPassword(userOld.getPassword());

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(item -> item.getArgument(0));

        var userResponse = userService.update(id, userOld);
        var userFilter = User.ofResponse(userResponse);

        assertThat(userFilter).isInstanceOf(User.class).isNotNull();
        assertThat(userFilter.getId()).isEqualTo(user.getId());
        assertThat(userFilter.getName()).isEqualTo(user.getName());
        assertThat(userFilter.getEmail()).isEqualTo(user.getEmail());
        assertThat(userFilter.getPassword()).isEqualTo(user.getPassword());
        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void generateNotFoundExceptionUpdateUser() {
        var user = new UserRequest("USER", "contato@hotmail.com", "123");
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(id, user))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Usuario não encontrado com o id: " + id);
        verify(userRepository, times(1)).findById(id);

    }

    @Test
    void allowDeleteByIdUser() {
        var user = UserHelper.createUser();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(id);

        userService.delete(id);

        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).deleteById(any(String.class));
    }

    @Test
    void generateNotFoundExceptionDeleteByIdUser() {
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.delete(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Usuario não encontrado com o id: " + id);
        verify(userRepository, times(1)).findById(id);
    }


}
