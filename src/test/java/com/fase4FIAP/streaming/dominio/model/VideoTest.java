package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoTest {

    @Test
    void deveConverterVideoRequestEmVideo() throws IOException {
        String title = "Breaking Bad";
        String description = "Piloto";
        String fileName = "breaking_bad.mp4";
        LocalDate publicationDate = LocalDate.of(2008,1,20);
        Category category = Category.ENTERTAINMENT;
        String favorite = ""; // TODO VERIFICAR A LÓGICA DO FAVORITO

        VideoRequest videoRequest = new VideoRequest(title, description, fileName, publicationDate.toString(), category.getCategoryName(), favorite);
        Video video = Video.of(fileName.getBytes(), videoRequest);

        assertEquals(title, video.getTitle());
        assertEquals(description, video.getDescription());
        assertEquals(fileName, video.getFileName());
        assertEquals(publicationDate, video.getPublicationDate());
        assertEquals(category, video.getCategory());
    }

    @Test
    void deveValidarIncrementoDeView(){

        String videoId = "123456";
        String title = "Breaking Bad";
        String description = "Piloto";
        String fileName = "breaking_bad.mp4";
        byte[] videoData = fileName.getBytes();
        LocalDate publicationDate = LocalDate.of(2008,1,20);
        Category category = Category.ENTERTAINMENT;
        long view = 0L;

        Video video = new Video();
        video.setVideoId(videoId);
        video.setTitle(title);
        video.setDescription(description);
        video.setFileName(fileName);
        video.setVideoData(videoData);
        video.setPublicationDate(publicationDate);
        video.setCategory(category);
        video.setView(view);

        video.incrementView();
        video.incrementView();
        video.incrementView();

        assertEquals(video.getView(), 3L);
    }
}
