package com.fase4FIAP.streaming.domain.dto.response;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {

  private UserResponse generateUserResponse(){
    var id = "123456";
    var name = "USER_REQUEST";
    var email = "request@hotmail.com";
    var password = "123";
    return new UserResponse(id, name, email, password);
  }

  @Test
  void allowHashCode() {
    var userResponse1 = generateUserResponse();
    var userResponse2 = generateUserResponse();

    assertEquals(userResponse1.hashCode(), userResponse2.hashCode());
  }

  @Test
  void allowToString(){
    String expectedString = "UserResponse(id=123456, name=USER_REQUEST, email=request@hotmail.com, password=123)";
    assertEquals(expectedString, generateUserResponse().toString());
  }
}
