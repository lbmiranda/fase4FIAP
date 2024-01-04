package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.RecommendationVideoService;
import com.fase4FIAP.streaming.domain.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video-recommendation")
public class RecommendationVideoController {

    private final RecommendationVideoService recommendationVideoService;

    @GetMapping
    public List<Video> recommendVideos(@RequestParam String userId) {
        return recommendationVideoService.recommendVideos(userId);
    }
}
