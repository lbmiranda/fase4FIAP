package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void deveConverterUsuarioRequestEmUsuario(){
        String name = "Usuário Teste";
        String email = "contato@hotmail.com";
        String password = "123ABC";

        UserRequest userResponse = new UserRequest(name, email, password);
        User user = User.of(userResponse);
        
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

}
