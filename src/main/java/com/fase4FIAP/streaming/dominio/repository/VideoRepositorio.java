package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.dominio.enums.Categoria;
import com.fase4FIAP.streaming.dominio.model.Video;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VideoRepositorio extends ReactiveCrudRepository<Video, String> {

    Flux<Video> findByTituloContaining(String titulo);

    Flux<Video> findByDataPublicacao(LocalDate data);

    Flux<Video> findAll();

    Mono<Video> findByNomeArquivo(String videoId);

    Flux<Video> findByCategoria(Categoria categoria);
}
