package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.FavoriteVideoService;
import com.fase4FIAP.streaming.useCase.implementation.RecommendationVideoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.fail;

class RecommendationVideoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecommendationVideoService recommendationVideoService;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        RecommendationVideoController recommendationVideoController = new RecommendationVideoController(recommendationVideoService);
        mockMvc = MockMvcBuilders.standaloneSetup(recommendationVideoController)
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
    void allowRecommnedVideos(){
        fail("Teste não implementado.");
    }
}
