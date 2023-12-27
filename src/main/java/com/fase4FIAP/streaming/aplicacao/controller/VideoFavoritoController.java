package com.fase4FIAP.streaming.aplicacao.controller;

import com.fase4FIAP.streaming.casoDeUso.impl.VideoFavoritoService;
import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video-favoritos")
public class VideoFavoritoController {

    private final VideoFavoritoService service;

    @PostMapping
    public VideoFavoritoResponse favoritar(@RequestBody @Valid VideoFavoritoRequest request) {
        return service.favoritar(request);
    }

    @GetMapping
    public List<VideoFavoritoResponse> getFavoritosPorUsuario(@RequestParam String usuarioId) {
        return service.getFavoritosPorUsuario(usuarioId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
