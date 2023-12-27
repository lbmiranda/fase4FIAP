package com.fase4FIAP.streaming.aplicacao.controller;

import com.fase4FIAP.streaming.casoDeUso.impl.VideoRecommendationService;
import com.fase4FIAP.streaming.dominio.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video-recommendation")
public class VideoRecommendationControler {

    private final VideoRecommendationService service;

    @GetMapping
    public List<Video> recommendVideos(@RequestParam String usuarioId) {
        return service.recommendVideos(usuarioId);
    }
}
