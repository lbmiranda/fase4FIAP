package com.fase4FIAP.streaming.video.controllers;

import com.fase4FIAP.streaming.video.entities.Video;
import com.fase4FIAP.streaming.video.useCases.VideoCasoDeUso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;
import java.time.LocalDate;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoCasoDeUso videoCasoDeUso;

    @Autowired
    public VideoController (VideoCasoDeUso videoCasoDeUso) {
        this.videoCasoDeUso = videoCasoDeUso;
    }

    @PostMapping
    public Mono<ResponseEntity<Video>> cadastrarVideo(@RequestBody Video video) {
        return videoCasoDeUso.cadastrarVideo(video)
                .map(savedVideo -> ResponseEntity.status(HttpStatus.CREATED).body(savedVideo))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/busca/titulo")
    public Flux<Video> buscaPorTitulo(@RequestParam String titulo, @PageableDefault(size = 10)Pageable pageable) {
        return videoCasoDeUso.getVideoPorTitulo(titulo, pageable);
    }

    @GetMapping("busca/data")
    public Flux<Video> buscaPorDataPublicacao(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                              @PageableDefault(size = 10) Pageable pageable) {
        return videoCasoDeUso.getVideoPorDataPublicacao(data, pageable);
    }

}
