package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.utils.UserHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UserRepositoryTest {

    final String id = "c1229ad8-e869-4cf0-bf1b-de193465248b";

    @Mock
    private UserRepository userRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void allowSaveUser() {
        var user = UserHelper.createUser();
        when(userRepository.save(any(User.class))).thenReturn(user);

        var userCreated = userRepository.save(user);

        assertThat(userCreated).isNotNull().isEqualTo(user);
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    void allowUpdateUser(){
        var user = UserHelper.createUser();
        var newId = "123ABC";
        user.setId(newId);
        user.setEmail("email_alterado@hotmail.com");
        user.setName("USER_UPDATE");

        when(userRepository.save(any(User.class))).thenAnswer(item -> item.getArgument(0));

        var userUpdate = userRepository.save(user);

        assertThat(userUpdate).isInstanceOf(User.class).isNotNull();
        assertThat(userUpdate.getId()).isEqualTo(newId);
        assertThat(userUpdate.getName()).isEqualTo(user.getName());
        assertThat(userUpdate.getEmail()).isEqualTo(user.getEmail());
        assertThat(userUpdate.getPassword()).isEqualTo(user.getPassword());
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void allowDeleteByIdUser() {
        doNothing().when(userRepository).deleteById(any(String.class));

        userRepository.deleteById(id);

        verify(userRepository, times(1)).deleteById(any(String.class));
    }


    @Test
    void allowFindByIdUser() {
        var user = UserHelper.createUser();
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(user));

        var userFilter = userRepository.findById(id);

        assertThat(userFilter).isPresent().containsSame(user);
        userFilter.ifPresent(search -> {
            assertThat(search.getId()).isEqualTo(user.getId());
            assertThat(search.getName()).isEqualTo(user.getName());
            assertThat(search.getEmail()).isEqualTo(user.getEmail());
            assertThat(search.getPassword()).isEqualTo(user.getPassword());
        });

        verify(userRepository, times(1)).findById(any(String.class));

    }


    @Test
    void allowFindAllUsers() {
        var user1 = UserHelper.createUser();
        var user2 = UserHelper.createUser();
        var user3 = UserHelper.createUser();
        var listUsers = Arrays.asList(user1, user2, user3);
        when(userRepository.findAll()).thenReturn(listUsers);

        var listFilter = userRepository.findAll();

        assertThat(listFilter).hasSize(3).containsExactlyInAnyOrder(user1, user2, user3);
        verify(userRepository, times(1)).findAll();
    }


}
