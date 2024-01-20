package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.utils.UserHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void allowConvertUser(){
        String name = "Usuário Teste";
        String email = "contato@hotmail.com";
        String password = "123ABC";

        var userResponse = new UserRequest(name, email, password);
        var user = User.of(userResponse);
        
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    void allowHashCode(){
        var user1 = UserHelper.createUser();
        var user2 = UserHelper.createUser();

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void allowToString(){
        var user = UserHelper.createUser();

        String expectedString = "User(id=123ABC, name=USER_ROLLER, email=contato@hotmail.com, password=25d55ad283aa400af464c76d713c07ad)";
        assertEquals(expectedString, user.toString());
    }

    @Test
    void allowBuilder(){
        var user = User.builder()
            .id("123ABC")
            .name("USER_ROLLER")
            .email("contato@hotmail.com")
            .password("25d55ad283aa400af464c76d713c07ad")
            .build();

        assertThat(user.getId()).isEqualTo("123ABC");
        assertThat(user.getName()).isEqualTo("USER_ROLLER");
        assertThat(user.getEmail()).isEqualTo("contato@hotmail.com");
        assertThat(user.getPassword()).isEqualTo("25d55ad283aa400af464c76d713c07ad");

        var anotherUser = User.builder()
            .id("123ABC")
            .name("USER_ROLLER")
            .email("contato@hotmail.com")
            .password("25d55ad283aa400af464c76d713c07ad")
            .build();

        assertThat(user).isEqualTo(anotherUser);
        assertEquals(user.hashCode(), anotherUser.hashCode());

    }

    @Test
    void allowBuilderToString(){
        var user = User.builder()
            .id("123ABC")
            .name("USER_ROLLER")
            .email("contato@hotmail.com")
            .password("25d55ad283aa400af464c76d713c07ad")
            .build();

        assertThat(user.toString()).hasToString("User(id=123ABC, name=USER_ROLLER, email=contato@hotmail.com, password=25d55ad283aa400af464c76d713c07ad)");
    }

}
