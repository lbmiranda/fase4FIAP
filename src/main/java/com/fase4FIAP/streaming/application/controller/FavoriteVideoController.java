package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.FavoriteVideoService;
import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites-videos")
public class FavoriteVideoController {

    private final FavoriteVideoService favoriteVideoService;

    @PostMapping
    public FavoriteVideoResponse favorite(@RequestBody @Valid FavoriteVideoRequest request) {
        return favoriteVideoService.favorite(request);
    }

    @GetMapping
    public List<FavoriteVideoResponse> getFavoritesByUser(@RequestParam String userId) {
        return favoriteVideoService.getFavoritesByUser(userId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        favoriteVideoService.delete(id);
    }
}
