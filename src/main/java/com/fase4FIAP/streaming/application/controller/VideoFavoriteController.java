package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.VideoFavoriteService;
import com.fase4FIAP.streaming.domain.dto.request.VideoFavoriteRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoFavoriteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video-favoritos")
public class VideoFavoriteController {

    private final VideoFavoriteService service;

    @PostMapping
    public VideoFavoriteResponse favorite(@RequestBody @Valid VideoFavoriteRequest request) {
        return service.favorite(request);
    }

    @GetMapping
    public List<VideoFavoriteResponse> getFavoritesByUser(@RequestParam String userId) {
        return service.getFavoritesByUser(userId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
