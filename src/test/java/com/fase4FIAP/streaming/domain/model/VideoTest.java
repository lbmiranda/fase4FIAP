package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.utils.UserHelper;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoTest {

    @Test
    void allowConvertVideo() throws IOException {
        String title = "Breaking Bad";
        String description = "Piloto";
        String fileName = "breaking_bad.mp4";
        LocalDate publicationDate = LocalDate.of(2008,1,20);
        Category category = Category.ENTERTAINMENT;
        String favorite = "";

        VideoRequest videoRequest = new VideoRequest(title, description, fileName, publicationDate.toString(), category.getCategoryName(), favorite);
        Video video = Video.of(fileName.getBytes(), videoRequest);

        assertEquals(title, video.getTitle());
        assertEquals(description, video.getDescription());
        assertEquals(fileName, video.getFileName());
        assertEquals(publicationDate, video.getPublicationDate());
        assertEquals(category, video.getCategory());
    }

    @Test
    void allowIncrementView(){

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

    @Test
    void allowHashCode(){
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();

        assertEquals(video1.hashCode(), video2.hashCode());
    }


    @Test
    void allowToString(){
        var video = VideoHelper.createVideo();

        String expectedString = "Video(videoId=123ABC, title=Breaking Bad, description=Pilote, videoData=[98, 114, 101, 97, 107, 105, 110, 103, 95, 98, 97, 100, 46, 109, 112, 52], fileName=breaking_bad, publicationDate=2008-01-20, category=ENTERTAINMENT, view=0)";
        assertEquals(expectedString, video.toString());
    }

    @Test
    void allowBuilder(){
        var video = Video.builder()
            .videoId("123ABC")
            .title("Breaking Bad")
            .description("Pilote")
            .videoData("breaking_bad.mp4".getBytes())
            .fileName("breaking_bad")
            .publicationDate(LocalDate.of(2008,1,20))
            .category(Category.ENTERTAINMENT)
            .view(0L)
            .build();

        assertThat(video.getVideoId()).isEqualTo("123ABC");
        assertThat(video.getTitle()).isEqualTo("Breaking Bad");
        assertThat(video.getDescription()).isEqualTo("Pilote");
        assertThat(video.getVideoData()).isEqualTo("breaking_bad.mp4".getBytes());
        assertThat(video.getFileName()).isEqualTo("breaking_bad");
        assertThat(video.getPublicationDate()).isEqualTo(LocalDate.of(2008,1,20));
        assertThat(video.getCategory()).isEqualTo(Category.ENTERTAINMENT);
        assertThat(video.getView()).isZero();

        var anotherVideo = Video.builder()
            .videoId("123ABC")
            .title("Breaking Bad")
            .description("Pilote")
            .videoData("breaking_bad.mp4".getBytes())
            .fileName("breaking_bad")
            .publicationDate(LocalDate.of(2008,1,20))
            .category(Category.ENTERTAINMENT)
            .view(0L)
            .build();

        assertThat(video).isEqualTo(anotherVideo);
    }

    @Test
    void allowBuilderToString(){
        var video = Video.builder()
            .videoId("123ABC")
            .title("Breaking Bad")
            .description("Pilote")
            .videoData("breaking_bad.mp4".getBytes())
            .fileName("breaking_bad")
            .publicationDate(LocalDate.of(2008,1,20))
            .category(Category.ENTERTAINMENT)
            .view(0L)
            .build();

        assertThat(video.toString()).isEqualTo("Video(videoId=123ABC, title=Breaking Bad, description=Pilote, videoData=[98, 114, 101, 97, 107, 105, 110, 103, 95, 98, 97, 100, 46, 109, 112, 52], fileName=breaking_bad, publicationDate=2008-01-20, category=ENTERTAINMENT, view=0)");

    }
}
