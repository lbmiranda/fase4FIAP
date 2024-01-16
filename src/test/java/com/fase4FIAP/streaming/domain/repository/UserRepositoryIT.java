package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.utils.UserHelper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryIT {

    final String id = "123ABC";

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void allowCreateTable() {
        var totalRegisters = userRepository.count();
        assertThat(totalRegisters).isPositive();
    }

    @Test
    @Order(2)
    void allowSaveUser(){
        var user = UserHelper.createUser();

        var userCreated = userRepository.save(user);

        assertThat(userCreated).isInstanceOf(User.class).isNotNull();
        assertThat(userCreated.getId()).isEqualTo(id);
        assertThat(userCreated.getName()).isEqualTo(user.getName());
        assertThat(userCreated.getEmail()).isEqualTo(user.getEmail());
        assertThat(userCreated.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    @Order(3)
    void allowFindByIdUser(){
        var userFiltered = userRepository.findById(id);

        assertThat(userFiltered).isPresent();
        userFiltered.ifPresent(user -> assertThat(user.getId()).isEqualTo(id));
    }

    @Test
    @Order(4)
    void allowUpdateUser(){
        var user = UserHelper.createUser();
        user.setEmail("email_alterado@hotmail.com");
        user.setName("USER_UPDATE");

        var userUpdate = userRepository.save(user);

        assertThat(userUpdate).isInstanceOf(User.class).isNotNull();
        assertThat(userUpdate.getId()).isEqualTo(id);
        assertThat(userUpdate.getName()).isEqualTo(user.getName());
        assertThat(userUpdate.getEmail()).isEqualTo(user.getEmail());
        assertThat(userUpdate.getPassword()).isEqualTo(user.getPassword());
    }



    @Test
    @Order(5)
    void allowFindAllUser(){
        var listOfUsers = userRepository.findAll();
        assertThat(listOfUsers).hasSizeGreaterThan(0);
    }

    @Test
    @Order(6)
    void allowDeleteByIdUser(){
        userRepository.deleteById(id);
        var userDeleted = userRepository.findById(id);

        assertThat(userDeleted).isEmpty();
    }

}
