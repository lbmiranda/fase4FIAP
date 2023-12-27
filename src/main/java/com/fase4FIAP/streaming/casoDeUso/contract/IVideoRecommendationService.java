package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.model.Video;

import java.util.List;

public interface IVideoRecommendationService {

    List<Video> recommendVideos(String usuarioId);
}
