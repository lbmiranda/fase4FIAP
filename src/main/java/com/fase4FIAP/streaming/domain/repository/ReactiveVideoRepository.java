package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface ReactiveVideoRepository extends ReactiveCrudRepository<Video, String> {

    Flux<Video> findByTitleContaining(String title);

    Flux<Video> findByPublicationDate(LocalDate date);

    Mono<Video> findByFileName(String videoId);

    Flux<Video> findByCategory(Category category);

    Flux<Video> findByTitleContainingIgnoreCase(String title);
}
