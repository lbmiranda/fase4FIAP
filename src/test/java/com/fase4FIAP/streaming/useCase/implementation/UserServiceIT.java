package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class UserServiceIT {

  final String id = "123ABC";

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Test
  void allowSaveUser() {
    var user = new UserRequest("USER_INTEGRATION", "integration@gmail.com", "integration");

    var userCreated = userService.create(user);
    assertThat(User.ofResponse(userCreated)).isNotNull().isInstanceOf(User.class);
    assertThat(userCreated.getId()).isNotNull();
    assertThat(userCreated.getName()).isEqualTo(user.getName());
    assertThat(userCreated.getEmail()).isEqualTo(user.getEmail());
    assertThat(userCreated.getPassword()).isEqualTo(user.getPassword());
  }

  @Test
  void allowGetAllUsers() {
    var listOfUsers = userService.getAll();

    assertThat(listOfUsers).hasSizeGreaterThan(0);
  }

//  @Test
//  void allowGetByIdUser() {
//    var userFiltered = userService.getById(id);
//
//    assertThat(userFiltered).isNotNull().isInstanceOf(User.class);
//    assertThat(userFiltered.getId()).isNotNull().isEqualTo(id);
//  }

  @Test
  void generateNotFoundExceptionGetByIdUser() {
    assertThatThrownBy(() -> userService.getById(id + "1"))
        .isInstanceOf(NotFoundException.class);
  }

//  @Test
//  void allowUpdateUser() {
//    var user = new UserRequest();
//    user.setName("USER_UPDATE");
//    user.setEmail("update@hotmail.com");
//    user.setPassword("password_update");
//
//    var userUpdated = userService.update(id, user);
//
//    assertThat(userUpdated.getId()).isEqualTo(id);
//    assertThat(userUpdated.getName()).isEqualTo(user.getName());
//    assertThat(userUpdated.getEmail()).isEqualTo(user.getEmail());
//    assertThat(userUpdated.getPassword()).isEqualTo(user.getPassword());
//  }

  @Test
  void generateNotFoundExceptionUpdateUser() {
    var idNotExist = id + "1";
    var user = new UserRequest();
    user.setName("USER_UPDATE");
    user.setEmail("update@hotmail.com");
    user.setPassword("password_update");

    assertThatThrownBy(() -> userService.update(idNotExist, user))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Usuario não encontrado com o id: " + idNotExist);
  }
//  @Test
//  void allowDeleteByIdUser() {
//    userService.delete(id);
//
//    var userDeleted = userRepository.existsById(id);
//
//    assertThat(userDeleted).isFalse();
//  }

  @Test
  void generateNotFoundExceptionDeleteByIdUser() {
    var idNotExists = id + "1";
    assertThatThrownBy(() -> userService.delete(idNotExists))
        .isInstanceOf(NotFoundException.class)
        .hasMessage("Usuario não encontrado com o id: " + idNotExists);
  }

}
