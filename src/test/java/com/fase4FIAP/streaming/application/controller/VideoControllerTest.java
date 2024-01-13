package com.fase4FIAP.streaming.application.controller;


import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.DeleteVideoResponse;
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
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
class VideoControllerTest {

  final String videoId = "123ABC";

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

    var reactiveVideo = new VideoRequest("Breaking Bad", "Piloto", file, "2000-01-01", Category.ENTERTAINMENT.getCategoryName(), "");
    var videoUploadResponse = new VideoUploadResponse(true, videoId);
    var response = "Video cadastrado com sucesso. Video ID: " + videoId;

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
    var file = "breaking_bad";
    MultipartFile mockMultipartFile = new MockMultipartFile(
        file,
        file + "txt",
        "text/plain",
        "Test file content".getBytes()
    );

    var video = VideoHelper.createVideo();
    var reactiveVideo = new VideoRequest("Breaking Bad", "Piloto", file, "2000-01-01", Category.ENTERTAINMENT.getCategoryName(), "");
    var videoUploadResponse = new VideoUploadResponse(false, video.getVideoId());
    var response = "Falha ao cadastrar o video.";

    BDDMockito.when(videoService.uploadVideo(any(MultipartFile.class), any(VideoRequest.class)))
        .thenReturn(Mono.just(videoUploadResponse));

    StepVerifier.create(videoController.uploadVideo(mockMultipartFile, reactiveVideo))
        .expectSubscription()
        .expectNextMatches(responseEntity ->
            responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST &&
                Objects.equals(responseEntity.getBody(), response)
        )
        .verifyComplete();
  }

  @Test
  void allowPlayVideo(){
    var videoByte = "video".getBytes();
    BDDMockito.when(videoService.getVideoContent(any(String.class))).thenReturn(Mono.just(videoByte));

    StepVerifier.create(videoController.playVideo(videoId))
        .expectSubscription()
        .expectNextMatches(responseEntity ->
            responseEntity.getStatusCode() == HttpStatus.OK
        )
        .verifyComplete();
  }

  @Test
  void generateNotFoundPlayVideo(){
    var newId = "789465";
    var response = "Vídeo não encontrado com ID: " + newId;
    BDDMockito.when(videoService.getVideoContent(any(String.class))).thenReturn(Mono.error(new NotFoundException(response)));

    StepVerifier.create(videoController.playVideo(newId))
        .expectSubscription()
        .expectErrorMatches(throwable ->
            throwable instanceof NotFoundException &&
                throwable.getMessage().equals(response)
        )
        .verify();
  }

  // TODO - Problema na validação final
  @Test
  void allowGetAllVideos(){
//    var video1 = VideoHelper.createVideo();
//    var video2 = VideoHelper.createVideo();
//    var video3 = VideoHelper.createVideo();
//    var listOfVideos = Arrays.asList(video1, video2, video3);
//    var page = 1;
//    var size = 10;
//    var startIndex = page * size;
//    var endIndex = Math.min((page + 1) * size, listOfVideos.size());
//    var pageOfVideos = new PageImpl<>(listOfVideos.subList(startIndex, endIndex), PageRequest.of(page, size), listOfVideos.size());
//
//    BDDMockito.when(videoService.getAllVideosPaginate(any(Integer.class), any(Integer.class)))
//        .thenReturn(pageOfVideos);

//    StepVerifier.create(videoController.getAllVideos(page, size))
//        .expectSubscription()
//        .expectStatus().isOk()
//        .expectBody(PageImpl.class)
//        .consumeWith(responseEntity -> {
//          PageImpl<?> pageResponse = (PageImpl<?>) responseEntity.getResponseBody();
//          assertThat(pageResponse.getNumber()).isEqualTo(page);
//          assertThat(pageResponse.getSize()).isEqualTo(size);
//        })
//        .verifyComplete();



  }

  @Test
  void allowFindVideoByTitle(){
//    var query = "Breaking Bad";
//    var video1 = VideoHelper.createVideo();
//    var video2 = VideoHelper.createVideo();
//    var video3 = VideoHelper.createVideo();
//    var listOfVideos = Arrays.asList(video1, video2, video3);
//    BDDMockito.when(videoService.findVideoByTitle(any(String.class)))
//        .thenReturn(ResponseEntity.ok().body(Flux.fromIterable(listOfVideos)));
//
//    StepVerifier.create(videoController.findVideoByTitle(query))
//        .expectSubscription()
//        .expectStatus().isOk()
//        .expectBodyList(Video.class)
//        .isEqualTo(listOfVideos)
//        .verify();

    fail("Teste não implementado");

  }

  @Test
  void allowGetCategories(){
    ExtendedModelMap model = new ExtendedModelMap();
    var categories = videoController.getCategories();

    model.addAttribute("categories", categories);

    assertArrayEquals(Category.values(), (Category[]) model.get("categories"));
  }

  @Test
  void allowDelete(){
    fail("teste não implementado");
  }

  @Test
  void allowDeleteVideo(){

    var deleteVideoResponse = new DeleteVideoResponse(true);
    BDDMockito.when(videoService.deleteVideo(any(String.class))).thenReturn(Mono.just(deleteVideoResponse));

    StepVerifier.create(videoController.deleteVideo(videoId))
        .expectSubscription()
        .expectNextMatches(responseEntity ->
            responseEntity.getStatusCode() == HttpStatus.OK
        )
        .verifyComplete();

  }

  @Test
  void generateBadRequestDeleteVideo(){
    var response = "Erro ao deletar o vídeo";
    var deleteVideoResponse = new DeleteVideoResponse(false);
    BDDMockito.when(videoService.deleteVideo(any(String.class))).thenReturn(Mono.just(deleteVideoResponse));

    StepVerifier.create(videoController.deleteVideo(videoId))
        .expectSubscription()
        .expectNextMatches(responseEntity ->
            responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST &&
                Objects.equals(responseEntity.getBody(), response)
        )
        .verifyComplete();

  }




}
