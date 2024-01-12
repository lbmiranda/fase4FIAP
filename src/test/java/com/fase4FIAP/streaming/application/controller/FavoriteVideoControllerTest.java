package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;
import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.useCase.implementation.FavoriteVideoService;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import com.fase4FIAP.streaming.utils.JsonString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FavoriteVideoControllerTest {

    final String baseUrl = "/favorites-videos";
    final String baseUrlID = "/favorites-videos/{id}";

    final String userId = "987654";
    final String videoId = "123456";

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
    void allowFavorite() throws Exception {
        var favoriteVideoRequest = new FavoriteVideoRequest(userId, videoId, LocalDate.now());
        var favoriteVideo = FavoriteVideo.of(favoriteVideoRequest);
        var favoriteVideoResponse = FavoriteVideoResponse.of(favoriteVideo);
        Mockito.when(favoriteVideoService.favorite(any(FavoriteVideoRequest.class))).thenReturn(favoriteVideoResponse);

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonString.asJsonString(favoriteVideoRequest)))
                .andExpect(status().isOk());

        verify(favoriteVideoService, times(1)).favorite(any(FavoriteVideoRequest.class));
    }

    @Test
    void allowGetFavoritesByUser() throws Exception {
        var favoriteVideoResponse1 = FavoriteVideoResponse.of(FavoriteVideoHelper.createFavoriteVideo());
        var favoriteVideoResponse2 = FavoriteVideoResponse.of(FavoriteVideoHelper.createFavoriteVideo());
        var favoriteVideoResponse3 = FavoriteVideoResponse.of(FavoriteVideoHelper.createFavoriteVideo());
        var listOfFavoritesVideos = Arrays.asList(favoriteVideoResponse1, favoriteVideoResponse2, favoriteVideoResponse3);
        when(favoriteVideoService.getFavoritesByUser(any(String.class))).thenReturn(listOfFavoritesVideos);

        mockMvc.perform(get(baseUrl).param("userId", userId)).andExpect(status().isOk());
        verify(favoriteVideoService, times(1)).getFavoritesByUser(userId);
    }

    @Test
    void allowDelete() throws Exception {
        doNothing().when(favoriteVideoService).delete(any(String.class));

        mockMvc.perform(delete(baseUrlID, userId)).andExpect(status().isOk());
        verify(favoriteVideoService, times(1)).delete(userId);
    }


}
