package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteVideoTest {

  @Test
  void allowConvertFavoriteVideo() {
    String userId = "123abc";
    String videoId = "181715";
    LocalDate creationDate = LocalDate.now();

    FavoriteVideoRequest favoriteVideoRequest = new FavoriteVideoRequest(userId, videoId, creationDate);
    FavoriteVideo favoriteVideo = FavoriteVideo.of(favoriteVideoRequest);

    assertEquals(userId, favoriteVideo.getUserId());
    assertEquals(videoId, favoriteVideo.getVideoId());
    assertEquals(creationDate, favoriteVideo.getCreationDate());
  }

  @Test
  void allowHashCode() {
    var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
    var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();

    assertEquals(favoriteVideo1.hashCode(), favoriteVideo2.hashCode());
  }

  @Test
  void allowToString() {
    var favoriteVideo = FavoriteVideoHelper.createFavoriteVideo();
    var now = LocalDate.now();

    String expectedString = "FavoriteVideo(id=123ABC, userId=987654, videoId=123456, creationDate=" + now + ")";
    assertEquals(expectedString, favoriteVideo.toString());
  }

  @Test
  void allowBuilder() {
    var favoriteVideo = FavoriteVideo.builder()
        .id("123ABC")
        .userId("987654")
        .videoId("123456")
        .creationDate(LocalDate.now())
        .build();

    assertThat(favoriteVideo.getId()).isEqualTo("123ABC");
    assertThat(favoriteVideo.getUserId()).isEqualTo("987654");
    assertThat(favoriteVideo.getVideoId()).isEqualTo("123456");
    assertThat(favoriteVideo.getCreationDate()).isEqualTo(LocalDate.now());

    var anotherFavoriteVideo = FavoriteVideo.builder()
        .id("123ABC")
        .userId("987654")
        .videoId("123456")
        .creationDate(LocalDate.now())
        .build();

    assertThat(favoriteVideo).isEqualTo(anotherFavoriteVideo);
  }

  @Test
  void allowBuilderToString(){
    var favoriteVideo = FavoriteVideo.builder()
        .id("123ABC")
        .userId("987654")
        .videoId("123456")
        .creationDate(LocalDate.now())
        .build();

    var now = LocalDate.now();

    assertThat(favoriteVideo.toString()).isEqualTo("FavoriteVideo(id=123ABC, userId=987654, videoId=123456, creationDate="+now+")");
  }


}
