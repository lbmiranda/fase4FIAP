package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;

import java.util.List;

public interface IVideoFavoritoService {

    VideoFavoritoResponse favoritar(VideoFavoritoRequest request);

    List<VideoFavoritoResponse> getFavoritosPorUsuario(String usuarioId);

    void delete(String id);
}
