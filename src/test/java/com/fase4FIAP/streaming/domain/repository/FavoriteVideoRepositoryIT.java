package com.fase4FIAP.streaming.domain.repository;


import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoriteVideoRepositoryIT {

  final String id = "123ABC";

  final String userId = "123ABC";

  final String videoId = "123ABC";

  @Autowired
  private FavoriteVideoRepository favoriteVideoRepository;

  @Test
  @Order(1)
  void allowCreateTable() {
    var totalRegisters = favoriteVideoRepository.count();
    assertThat(totalRegisters).isPositive();
  }

  @Test
  @Order(2)
  void allowSaveFavoriteVideo() {
    var favoriteVideo = new FavoriteVideo(id, userId, videoId, LocalDate.now());

    var favoriteVideoCreated = favoriteVideoRepository.save(favoriteVideo);

    assertThat(favoriteVideoCreated).isInstanceOf(FavoriteVideo.class).isNotNull();
    assertThat(favoriteVideoCreated.getId()).isEqualTo(id);
    assertThat(favoriteVideoCreated.getUserId()).isEqualTo(favoriteVideo.getUserId());
    assertThat(favoriteVideoCreated.getVideoId()).isEqualTo(favoriteVideo.getVideoId());
    assertThat(favoriteVideoCreated.getCreationDate()).isEqualTo(favoriteVideo.getCreationDate());
  }

  @Test
  @Order(3)
  void allowFindById(){
    var videoFiltered = favoriteVideoRepository.findById(id);

    assertThat(videoFiltered).isPresent();
    videoFiltered.ifPresent(video -> assertThat(video.getId()).isEqualTo(id));
  }

  @Test
  @Order(4)
  void allowFindByUserId() {

    var listOfFavoritesVideos = favoriteVideoRepository.findByUserId(userId);

    assertThat(listOfFavoritesVideos).hasSizeGreaterThan(0);
  }

  @Test
  @Order(5)
  void allowFindAllVideos() {
    var listOfFavoritesVideos = favoriteVideoRepository.findAll();

    assertThat(listOfFavoritesVideos).hasSizeGreaterThan(0);
  }

  @Test
  @Order(6)
  void allowFindByUserIdAndVideoIdVideo() {
    var favoritesVideos = favoriteVideoRepository.findByUserIdAndVideoId(userId, videoId);

    System.out.println("FAVORITES VIDEOS: " + favoritesVideos);

    assertThat(favoritesVideos).isNotNull();
  }


  @Test
  @Order(7)
  void allowDeleteByIdVideo() {
    favoriteVideoRepository.deleteById(id);
    var userDeleted = favoriteVideoRepository.findById(id);

    assertThat(userDeleted).isEmpty();
  }


}
