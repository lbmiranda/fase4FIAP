package com.fase4FIAP.streaming.dominio;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VideoRepositorio extends ReactiveCrudRepository<VideoModelo, String> {

    Flux<VideoModelo> findByTituloContaining(String titulo);

    Flux<VideoModelo> findByDataPublicacao(LocalDate data);

    Flux<VideoModelo> findAll();

    Mono<VideoModelo> findByNomeArquivo(String videoId);

    Flux<VideoModelo> findByTituloContainingIgnoreCase(String titulo);
}
