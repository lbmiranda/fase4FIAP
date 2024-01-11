package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.useCase.implementation.UserService;
import com.fase4FIAP.streaming.utils.JsonString;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fase4FIAP.streaming.utils.JsonString.asJsonString;
import static org.junit.jupiter.api.Assertions.fail;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class UserControllerTest {

    final String baseUrl = "/users";
    final String baseUrlID = "/users/{id}";

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void allowCreateUser() throws Exception{
        var user = new UserRequest("USER", "contato@hotmail.com", "12345678");
        when(userService.create(any(UserRequest.class))).thenAnswer(i -> i.getArgument(0));

        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isCreated());

        verify(userService, times(1)).create(user);

    }

    @Test
    void allowGetAllUser(){
        fail("Teste não implementado");
    }

    @Test
    void allowGetByIdUser(){
        fail("Teste não implementado");
    }

    @Test
    void allowUpdateUser(){
        fail("Teste não implementado");
    }

    @Test
    void allowDeleteUser(){
        fail("Teste não implementado");
    }


}
