package com.fase4FIAP.streaming.video.repositories;

import com.fase4FIAP.streaming.video.entities.Video;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.awt.print.Pageable;
import java.time.LocalDate;

@Repository
public interface RepositorioVideo extends ReactiveCrudRepository<Video,String> {

    Flux<Video> findByTituloContaining(String titulo, Pageable pageable);
    Flux<Video> findByDataPublicacao(LocalDate data, Pageable pageable);

}
