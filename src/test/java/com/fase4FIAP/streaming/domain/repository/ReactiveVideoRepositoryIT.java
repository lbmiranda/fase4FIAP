package com.fase4FIAP.streaming.domain.repository;


import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
class ReactiveVideoRepositoryIT {

  final String videoId = "123ABC";

  @Autowired
  private ReactiveVideoRepository reactiveVideoRepository;

  @Test
  void allowSaveReactiveVideo(){
    var video = VideoHelper.createVideo();

    var videoCreated = reactiveVideoRepository.save(video).block();

    assertThat(videoCreated).isInstanceOf(Video.class).isNotNull();
    assert videoCreated != null;
    assertThat(videoCreated.getVideoId()).isEqualTo(videoId);
    assertThat(videoCreated.getTitle()).isEqualTo(video.getTitle());
    assertThat(videoCreated.getDescription()).isEqualTo(video.getDescription());
    assertThat(videoCreated.getVideoData()).isEqualTo(video.getVideoData());
    assertThat(videoCreated.getFileName()).isEqualTo(video.getFileName());
    assertThat(videoCreated.getPublicationDate()).isEqualTo(video.getPublicationDate());
    assertThat(videoCreated.getCategory()).isEqualTo(video.getCategory());
    assertThat(videoCreated.getView()).isEqualTo(video.getView());
  }

  @Test
  void allowFindByIdReactiveVideo(){
    var videoFiltered = reactiveVideoRepository.findById(videoId);

    assertThat(videoFiltered).isNotNull();
  }

  @Test
  void allowFindAllReactiveVideo(){
    var listOfVideos = reactiveVideoRepository.findAll().collectList().block();

    assertThat(listOfVideos).hasSizeGreaterThan(0);

  }

  @Test
  void allowFindByCategoryReactiveVideo(){
    var category = Category.ENTERTAINMENT;

    var listOfCategories = reactiveVideoRepository.findByCategory(category).collectList().block();

    assertThat(listOfCategories).hasSizeGreaterThan(0);
  }
  @Test
  void allowFindByTitleContainingIgnoreCaseReactiveVideo(){
    var title = "Breaking Bad";

    var listOfVideos = reactiveVideoRepository.findByTitleContainingIgnoreCase(title).collectList().block();

    assertThat(listOfVideos).hasSizeGreaterThan(0);
  }
  @Test
  void allowDeleteByIdReactiveVideo(){
    reactiveVideoRepository.deleteById(videoId).block();
    var videoDeleted = reactiveVideoRepository.findById(videoId).block();

    assertThat(videoDeleted).isNull();
  }
}
