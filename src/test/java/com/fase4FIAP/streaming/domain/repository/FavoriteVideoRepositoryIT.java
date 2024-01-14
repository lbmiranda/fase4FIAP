package com.fase4FIAP.streaming.domain.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
class FavoriteVideoRepositoryIT {

  @Autowired
  private FavoriteVideoRepository favoriteVideoRepository;

  @Test
  void allowSaveFavoriteVideo() {
    fail("teste não implementado.");
  }

  @Test
  void allowFindByUserId() {
    fail("teste não implementado.");
  }

  @Test
  void allowFindAllVideos() {
    fail("teste não implementado.");
  }

  @Test
  void allowDeleteByIdVideo() {
    fail("teste não implementado.");
  }

  @Test
  void allowFindByUserIdAndVideoIdVideo() {
    fail("teste não implementado.");
  }

  @Test
  void allowFindById(){
    fail("teste não implementado.");
  }


}
