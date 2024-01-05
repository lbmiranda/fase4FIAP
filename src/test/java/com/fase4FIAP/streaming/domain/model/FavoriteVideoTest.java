package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteVideoTest {

    @Test
    void deveConverterVideoFavoritoRequestEmVideoFavorito(){
        String userId = "123abc";
        String videoId = "181715";
        LocalDate creationDate = LocalDate.now();

        FavoriteVideoRequest favoriteVideoRequest = new FavoriteVideoRequest(userId, videoId, creationDate);
        FavoriteVideo favoriteVideo = FavoriteVideo.of(favoriteVideoRequest);

        assertEquals(userId, favoriteVideo.getUserId());
        assertEquals(videoId, favoriteVideo.getVideoId());
        assertEquals(creationDate, favoriteVideo.getCreationDate());
    }
}
