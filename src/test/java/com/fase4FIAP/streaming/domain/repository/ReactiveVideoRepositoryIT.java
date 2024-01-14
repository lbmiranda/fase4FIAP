package com.fase4FIAP.streaming.domain.repository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
class ReactiveVideoRepositoryIT {

  @Autowired
  private ReactiveVideoRepository reactiveVideoRepository;

  @Test
  void allowSaveReactiveVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowFindByIdReactiveVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowFindAllReactiveVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowDeleteByIdReactiveVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowFindByCategoryReactiveVideo(){
    fail("teste não implementado");
  }

  @Test
  void allowFindByTitleContainingIgnoreCaseReactiveVideo(){
    fail("teste não implementado");
  }
}
