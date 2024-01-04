package com.fase4FIAP.streaming.utils;

import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.domain.model.User;

import java.time.LocalDate;

public abstract class FavoriteVideoHelper {

    public static FavoriteVideo createFavoriteVideo() {
        return FavoriteVideo.builder()
                .id("123ABC")
                .userId("987654")
                .videoId("123456")
                .creationDate(LocalDate.now())
                .build();
    }
}

