package com.fase4FIAP.streaming.video.services;

import com.fase4FIAP.streaming.video.entities.Video;
import com.fase4FIAP.streaming.video.repositories.RepositorioVideo;
import com.fase4FIAP.streaming.video.useCases.VideoCasoDeUso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;
import java.time.LocalDate;

@Service
public class VideoService implements VideoCasoDeUso {

    private final RepositorioVideo repositorioVideo;

    @Autowired
    public VideoService (RepositorioVideo repositorioVideo) {
        this.repositorioVideo = repositorioVideo;
    }

    @Override
    public Flux<Video> getVideoPorTitulo(String titulo, Pageable pageable) {
        return repositorioVideo.findByTituloContaining(titulo, pageable);
    }

    @Override
    public Flux<Video> getVideoPorDataPublicacao(LocalDate data, Pageable pageable) {
        return repositorioVideo.findByDataPublicacao(data, pageable);
    }

    @Override
    public Mono<Video> cadastrarVideo(Video video) {
        return repositorioVideo.save(video);
    }


}
