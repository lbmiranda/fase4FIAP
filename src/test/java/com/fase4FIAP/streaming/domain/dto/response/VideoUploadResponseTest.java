package com.fase4FIAP.streaming.domain.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoUploadResponseTest {

  private VideoUploadResponse generateVideoUploadResponse(){
    var success = true;
    var videoId = "123ABC";
    return new VideoUploadResponse(success, videoId);
  }

  @Test
  void allowHashCode() {
    var videoUploadResponse1 = generateVideoUploadResponse();
    var videoUploadResponse2 = generateVideoUploadResponse();

    assertEquals(videoUploadResponse1.hashCode(), videoUploadResponse2.hashCode());
  }

  @Test
  void allowToString(){
    String expectedString = "VideoUploadResponse(success=true, videoId=123ABC)";
    assertEquals(expectedString, generateVideoUploadResponse().toString());
  }

}
