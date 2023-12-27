package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.aplicacao.exceptions.AlreadyFavoritedException;
import com.fase4FIAP.streaming.aplicacao.exceptions.NotFoundException;
import com.fase4FIAP.streaming.casoDeUso.contract.IVideoFavoritoService;
import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;
import com.fase4FIAP.streaming.dominio.model.VideoFavorito;
import com.fase4FIAP.streaming.dominio.repository.VideoFavoritoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoFavoritoService implements IVideoFavoritoService {

    private final VideoFavoritoRepositorio videoFavoritoRepositorio;

    @Override
    public VideoFavoritoResponse favoritar(VideoFavoritoRequest request) {
        verificarSeJaFavoritou(request);
        return VideoFavoritoResponse.of(videoFavoritoRepositorio.save(VideoFavorito.of(request)));
    }

    @Override
    public List<VideoFavoritoResponse> getFavoritosPorUsuario(String usuarioId) {
        return videoFavoritoRepositorio.findByUsuarioId(usuarioId)
                .stream()
                .map(VideoFavoritoResponse::of)
                .toList();
    }

    @Override
    public List<VideoFavoritoResponse> getAll() {
        return videoFavoritoRepositorio.findAll()
                .stream()
                .map(VideoFavoritoResponse::of)
                .toList();
    }

    @Override
    public void delete(String id) {
        findById(id);
        videoFavoritoRepositorio.deleteById(id);
    }

    private void verificarSeJaFavoritou(VideoFavoritoRequest request) {
        var existente = videoFavoritoRepositorio.findByUsuarioIdAndVideoId(request.getUsuarioId(), request.getVideoId());

        if (existente.isPresent()) {
            throw new AlreadyFavoritedException("O vídeo já está favoritado pelo usuário.");
        }
    }

    private void findById(String id) {
        videoFavoritoRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Video favorito não encontrado com id: " + id));
    }
}
