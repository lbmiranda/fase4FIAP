package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.application.exceptions.dto.ErrorDetails;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.useCase.implementation.UserService;
import com.fase4FIAP.streaming.utils.UserHelper;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class UserControllerTest {

    final String baseUrl = "/users";
    final String baseUrlID = "/users/{id}";

    String id = "123ABC";

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
        var userRequest = new UserRequest("USER", "contato@hotmail.com", "12345678");
        var user = User.of(userRequest);
        var userResponse = UserResponse.of(user);
        when(userService.create(any(UserRequest.class))).thenReturn(userResponse);

        Gson gson = new Gson();
        String jsonUser = gson.toJson(userRequest);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk());

        verify(userService, times(1)).create(any(UserRequest.class));

    }

    @Test
    void allowGetAllUser() throws Exception{
        var user1 = UserResponse.of(UserHelper.createUser());
        var user2 = UserResponse.of(UserHelper.createUser());
        var user3 = UserResponse.of(UserHelper.createUser());
        var listOfUsers = Arrays.asList(user1, user2, user3);
        when(userService.getAll()).thenReturn(listOfUsers);

        mockMvc.perform(get(baseUrl)).andExpect(status().isOk());
        verify(userService, times(1)).getAll();
    }

    @Test
    void allowGetByIdUser() throws Exception{
        var user = UserHelper.createUser();
        var userResponse = UserResponse.of(user);
        when(userService.findById(any(String.class))).thenReturn(user);
        when(userService.getById(any(String.class))).thenReturn(userResponse);

        mockMvc.perform(get(baseUrlID, id)).andExpect(status().isOk());
        verify(userService, times(1)).getById(id);
    }

    // TODO - Espera receber 404 mas recebe 200
//    @Test
//    void generateExceptionNotFoundException() throws Exception {
//
//        var newId = "785496";
//        var message = "Usuario não encontrado com o id: " + newId;
//        when(userService.findById(any(String.class)))
//            .thenThrow(NotFoundException.class);
//
//        mockMvc.perform(get(baseUrlID, newId))
//            .andExpect(status().isNotFound());
//
//
//        verify(userService, times(1)).findById(newId);
//    }

    @Test
    void allowUpdateUser() throws Exception{
        var userRequest = new UserRequest("USER", "contato@hotmail.com", "12345678");
        var user = User.of(userRequest);
        var userResponse = UserResponse.of(user);
        when(userService.update(any(String.class), any(UserRequest.class))).thenReturn(userResponse);

        Gson gson = new Gson();
        String jsonUser = gson.toJson(userRequest);

        mockMvc.perform(put(baseUrlID, id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk());

        verify(userService, times(1)).update(id, userRequest);
    }

    @Test
    void allowDeleteUser() throws Exception{
        doNothing().when(userService).delete(id);

        mockMvc.perform(delete(baseUrlID, id)
                ).andExpect(status().isOk());

        verify(userService, times(1)).delete(any(String.class));
    }


}
