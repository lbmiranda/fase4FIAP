package com.fase4FIAP.streaming.video.controllers;

import com.fase4FIAP.streaming.video.entities.Video;
import com.fase4FIAP.streaming.video.useCases.VideoCasoDeUso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
