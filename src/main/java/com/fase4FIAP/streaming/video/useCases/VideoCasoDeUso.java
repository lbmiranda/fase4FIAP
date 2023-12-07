package com.fase4FIAP.streaming.video.useCases;

import com.fase4FIAP.streaming.video.entities.Video;
import reactor.core.publisher.Flux;

import java.awt.print.Pageable;
import java.time.LocalDate;

public interface VideoCasoDeUso {

    Flux<Video> getVideoPorTitulo(String titulo, Pageable pageable);
    Flux<Video> getVideoPorDataPublicacao(LocalDate data, Pageable pageable);



}
