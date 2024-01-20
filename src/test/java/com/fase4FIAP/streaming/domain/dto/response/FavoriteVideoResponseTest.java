package com.fase4FIAP.streaming.domain.dto.response;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteVideoResponseTest {

  private FavoriteVideoResponse generateVideoResponse(){
    var id = "789";
    var userId = "123ABC";
    var videoId = "456ABC";
    var creationDate = LocalDate.now();
    return new FavoriteVideoResponse(id, userId, videoId, creationDate);
  }

  @Test
  void allowHashCode() {
    var favoriteVideoResponse1 = generateVideoResponse();
    var favoriteVideoResponse2 = generateVideoResponse();

    assertEquals(favoriteVideoResponse1.hashCode(), favoriteVideoResponse2.hashCode());
  }

  @Test
  void allowEquals(){
    var favoriteVideo1 = generateVideoResponse();
    var favoriteVideo2 = generateVideoResponse();

    assertThat(favoriteVideo1.equals(favoriteVideo2)).isTrue();
  }
  @Test
  void allowCanEquals() {
    var favoriteVideo1 = generateVideoResponse();
    var favoriteVideo2 = generateVideoResponse();

    assertThat(favoriteVideo1.canEqual(favoriteVideo2)).isTrue();
  }

  @Test
  void allowToString(){
    var now = LocalDate.now();
    String expectedString = "FavoriteVideoResponse(id=789, userId=123ABC, videoId=456ABC, creationDate="+now+")";
    assertEquals(expectedString, generateVideoResponse().toString());
  }

}
