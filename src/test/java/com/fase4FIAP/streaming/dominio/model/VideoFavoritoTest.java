package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoFavoritoTest {

    @Test
    void deveConverterVideoFavoritoRequestEmVideoFavorito(){
        String usuarioId = "123abc";
        String videoId = "181715";
        LocalDate dataCriacao = LocalDate.now();

        VideoFavoritoRequest videoFavoritoRequest = new VideoFavoritoRequest(usuarioId, videoId, dataCriacao);
        VideoFavorito videoFavorito = VideoFavorito.of(videoFavoritoRequest);

        assertEquals(usuarioId, videoFavorito.getUsuarioId());
        assertEquals(videoId, videoFavorito.getVideoId());
        assertEquals(dataCriacao, videoFavorito.getDataCriacao());
    }
}
