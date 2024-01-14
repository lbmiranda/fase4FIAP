package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.RecommendationVideoService;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecommendationVideoControllerTest {

    final String baseUrl = "/video-recommendation";

    final String userId = "987654";

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
    void allowRecommnedVideos() throws Exception {
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfVideos = Arrays.asList(video1, video2, video3);
        Mockito.when(recommendationVideoService.recommendVideos(any(String.class))).thenReturn(listOfVideos);

        mockMvc.perform(get(baseUrl).param("userId", userId)).andExpect(status().isOk());
        verify(recommendationVideoService, times(1)).recommendVideos(userId);
    }
}
