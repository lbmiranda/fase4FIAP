package com.fase4FIAP.streaming.domain.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoStatisticResponseTest {

  private VideoStatisticResponse generateVideoStatisticResponse(){
    var totalVideos = 3L;
    var totalFavorites = 3L;
    var previewAverage = 5;
    return new VideoStatisticResponse(totalVideos, totalFavorites, previewAverage);
  }

  @Test
  void allowHashCode() {
    var videoStatisticResponse1 = generateVideoStatisticResponse();
    var videoStatisticResponse2 = generateVideoStatisticResponse();

    assertEquals(videoStatisticResponse1.hashCode(), videoStatisticResponse2.hashCode());
  }

  @Test
  void allowToString(){
    String expectedString = "VideoStatisticResponse(totalVideos=3, totalFavorites=3, previewAverage=5.0)";
    assertEquals(expectedString, generateVideoStatisticResponse().toString());
  }

}
