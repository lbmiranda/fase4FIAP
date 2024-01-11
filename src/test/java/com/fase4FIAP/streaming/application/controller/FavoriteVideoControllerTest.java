package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.FavoriteVideoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.fail;

class FavoriteVideoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FavoriteVideoService favoriteVideoService;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        FavoriteVideoController favoriteVideoController = new FavoriteVideoController(favoriteVideoService);
        mockMvc = MockMvcBuilders.standaloneSetup(favoriteVideoController)
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
    void allowFavorite(){
        fail("Teste não implementado.");
    }

    @Test
    void allowGetFavoritesByUser(){
        fail("Teste não implementado.");
    }

    @Test
    void allowDelete(){
        fail("Teste não implementado.");
    }
}
