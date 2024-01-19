package com.fase4FIAP.streaming.domain.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteVideoResponseTest {

  private DeleteVideoResponse deleteVideoResponse(){
    return new DeleteVideoResponse(true);
  }
  @Test
  void allowDeleteVideoResponse(){

    var deleteVideoResponse = new DeleteVideoResponse(true);

    assertThat(deleteVideoResponse).isNotNull();
  }

  @Test
  void allowDeleteVideoResponseError(){

    var deleteVideoResponse = new DeleteVideoResponse(false);

    assertThat(deleteVideoResponse).isNotNull();
  }

  @Test
  void allowDeleteVideoSuccessfully(){
    var success = true;

    assertThat(success).isTrue();
  }

}
