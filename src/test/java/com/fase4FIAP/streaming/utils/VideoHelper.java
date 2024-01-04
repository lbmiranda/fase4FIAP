package com.fase4FIAP.streaming.utils;

import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.domain.model.Video;

import java.time.LocalDate;

public abstract class VideoHelper {

    public static Video createVideo() {
        return Video.builder()
                .videoId("123ABC")
                .title("Breaking Bad")
                .description("Pilote")
                .videoData("breaking_bad.mp4".getBytes())
                .fileName("breaking_bad")
                .publicationDate(LocalDate.of(2008,1,20))
                .category(Category.ENTERTAINMENT)
                .view(0L)
                .build();
    }
}

