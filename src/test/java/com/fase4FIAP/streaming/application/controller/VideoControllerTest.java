package com.fase4FIAP.streaming.application.controller;


import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.useCase.implementation.VideoService;

import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
class VideoControllerTest {

  final String baseUrl = "/videos";

  @InjectMocks
  private VideoController videoController;

  @Mock
  private VideoService videoService;

  @Test
  void allowUploadVideo(){
    var file = "breaking_bad";
    MultipartFile mockMultipartFile = new MockMultipartFile(
        file,
        file + "txt",
        "text/plain",
        "Test file content".getBytes()
    );

    var video = VideoHelper.createVideo();
    var reactiveVideo = new VideoRequest("Breaking Bad", "Piloto", file, "2000-01-01", Category.ENTERTAINMENT.getCategoryName(), "");
    var videoUploadResponse = new VideoUploadResponse(true, video.getVideoId());
    var response = "Video cadastrado com sucesso. Video ID: " + video.getVideoId();

    BDDMockito.when(videoService.uploadVideo(any(MultipartFile.class), any(VideoRequest.class)))
        .thenReturn(Mono.just(videoUploadResponse));

    StepVerifier.create(videoController.uploadVideo(mockMultipartFile, reactiveVideo))
        .expectSubscription()
        .expectNextMatches(responseEntity ->
            responseEntity.getStatusCode() == HttpStatus.OK &&
                Objects.equals(responseEntity.getBody(), response)
        )
        .verifyComplete();
  }

  @Test
  void generateBadRequestUploadVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowPlayVideo(){
    fail("teste não implementado");
  }

  @Test
  void generateNotFoundPlayVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowGetAllVideos(){
    fail("teste não implementado");
  }

  @Test
  void allowFindVideoByTitle(){
    fail("teste não implementado");
  }

  @Test
  void allowGetCategories(){
    fail("teste não implementado");
  }

  @Test
  void allowDeleteVideo(){
    fail("teste não implementado");
  }

  @Test
  void generateBadRequestDeleteVideo(){
    fail("teste não implementado");
  }





}
