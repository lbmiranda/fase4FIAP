package com.fase4FIAP.streaming.domain.dto.request;

import com.fase4FIAP.streaming.domain.enums.Category;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoRequestTest {

  private VideoRequest generateVideoRequest() {
    var title = "Breaking Bad";
    var description = "Piloto";
    var fileName = "breaking_bad";
    var publicationDate = LocalDate.of(2008, 1, 1).toString();
    var category = Category.ENTERTAINMENT.getCategoryName();
    var favorite = "";
    return new VideoRequest(title, description, fileName, publicationDate, category, favorite);
  }

  @Test
  void allowHashCode() {
    var videoRequest1 = generateVideoRequest();
    var videoRequest2 = generateVideoRequest();

    assertEquals(videoRequest1.hashCode(), videoRequest2.hashCode());
  }

  @Test
  void allowEquals() {
    var favoriteVideo = generateVideoRequest();

    assertThat(favoriteVideo.equals(favoriteVideo)).isTrue();
  }

  @Test
  void allowToString() {
    String expectedString = "VideoRequest(title=Breaking Bad, description=Piloto, fileName=breaking_bad, publicationDate=2008-01-01, category=Entertainment, favorite=)";
    assertEquals(expectedString, generateVideoRequest().toString());
  }

  @Test
  void allowSetVideo() {
    var video = generateVideoRequest();
    video.setTitle("Video update");
    video.setDescription("Description update");
    video.setFileName("file_name_update");
    video.setPublicationDate(LocalDate.of(2024, 1, 1).toString());
    video.setCategory(Category.MUSIC.getCategoryName());
    video.setFavorite("");

    assertThat(video.getTitle()).isEqualTo("Video update");
    assertThat(video.getDescription()).isEqualTo("Description update");
    assertThat(video.getFileName()).isEqualTo("file_name_update");
    assertThat(video.getPublicationDate()).isEqualTo(LocalDate.of(2024, 1, 1).toString());
    assertThat(video.getCategory()).isEqualTo(Category.MUSIC.getCategoryName());
    assertThat(video.getFavorite()).isEmpty();
  }


  @Test
  void allowBuilder() {
    var video = VideoRequest.builder()
        .title("Breaking Bad")
        .description("Pilote")
        .fileName("breaking_bad")
        .publicationDate(LocalDate.of(2008,1,20).toString())
        .category(Category.ENTERTAINMENT.getCategoryName())
        .build();

    assertThat(video.getTitle()).isEqualTo("Breaking Bad");
    assertThat(video.getDescription()).isEqualTo("Pilote");
    assertThat(video.getFileName()).isEqualTo("breaking_bad");
    assertThat(video.getPublicationDate()).isEqualTo(LocalDate.of(2008,1,20).toString());
    assertThat(video.getCategory()).isEqualTo(Category.ENTERTAINMENT.getCategoryName());

    var anotherVideo = VideoRequest.builder()
        .title("Breaking Bad")
        .description("Pilote")
        .fileName("breaking_bad")
        .publicationDate(LocalDate.of(2008,1,20).toString())
        .category(Category.ENTERTAINMENT.getCategoryName())
        .build();

    assertThat(video).isEqualTo(anotherVideo);
  }

  @Test
  void allowVideoRequestNoArgsConstructor(){
    var videoRequest = new VideoRequest();

    assertThat(videoRequest.getTitle()).isNull();
    assertThat(videoRequest.getFileName()).isNull();
    assertThat(videoRequest.getPublicationDate()).isNull();
    assertThat(videoRequest.getCategory()).isNull();
    assertThat(videoRequest.getFavorite()).isNull();
  }

  @Test
  void allowBuilderToString(){
    var video = VideoRequest.builder()
        .title("Breaking Bad")
        .description("Pilote")
        .fileName("breaking_bad")
        .publicationDate(LocalDate.of(2008,1,20).toString())
        .category(Category.ENTERTAINMENT.toString())
        .build();

    assertThat(video.toString()).hasToString("VideoRequest(title=Breaking Bad, description=Pilote, fileName=breaking_bad, publicationDate=2008-01-20, category=ENTERTAINMENT, favorite=null)");

  }


}
