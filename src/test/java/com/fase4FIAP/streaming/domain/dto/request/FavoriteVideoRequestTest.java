package com.fase4FIAP.streaming.domain.dto.request;

import lombok.Getter;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FavoriteVideoRequestTest {

   private FavoriteVideoRequest generateVideoRequest(){
     var userId = "123ABC";
     var videoId = "456ABC";
     var creationDate = LocalDate.now();
     return new FavoriteVideoRequest(userId, videoId, creationDate);
   }

  @Test
  void allowHashCode() {
    var favoriteVideoRequest1 = generateVideoRequest();
    var favoriteVideoRequest2 = generateVideoRequest();

    assertEquals(favoriteVideoRequest1.hashCode(), favoriteVideoRequest2.hashCode());
  }

  @Test
  void allowEquals(){
     var favoriteVideo = generateVideoRequest();

    assertThat(favoriteVideo.equals(favoriteVideo)).isTrue();
  }

  @Test
  void allowToString(){
    var now = LocalDate.now();
    String expectedString = "FavoriteVideoRequest(userId=123ABC, videoId=456ABC, creationDate="+now+")";
    assertEquals(expectedString, generateVideoRequest().toString());
  }
}
