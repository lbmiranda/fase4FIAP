package com.fase4FIAP.streaming.domain.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRequestTest {

  private UserRequest generateUserRequest(){
    var name = "USER_REQUEST";
    var email = "request@hotmail.com";
    var password = "123";
    return new UserRequest(name, email, password);
  }

  @Test
  void allowHashCode() {
    var userRequest1 = generateUserRequest();
    var userRequest2 = generateUserRequest();

    assertEquals(userRequest1.hashCode(), userRequest2.hashCode());
  }

  @Test
  void allowToString(){
    String expectedString = "UserRequest(name=USER_REQUEST, email=request@hotmail.com, password=123)";
    assertEquals(expectedString, generateUserRequest().toString());
  }
}
