package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;
import com.fase4FIAP.streaming.dominio.model.Video;

import java.util.List;

public interface IVideoFavoritoService {

    VideoFavoritoResponse favoritar(VideoFavoritoRequest request);

    List<VideoFavoritoResponse> getFavoritosPorUsuario(String usuarioId);

    List<VideoFavoritoResponse> getAll();

    void delete(String id);
}
