package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.casoDeUso.contract.IEstatisticasService;
import com.fase4FIAP.streaming.dominio.dto.response.EstatisticasVideoResponse;
import com.fase4FIAP.streaming.dominio.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticasService implements IEstatisticasService {

    private final VideoService videoService;
    private final VideoFavoritoService videoFavoritoService;

    @Override
    public EstatisticasVideoResponse calcularEstatisticas() {
        var totalVideos = videoService.getAllVideos().count().block();
        var totalFavoritos = videoFavoritoService.getAll().size();
        var mediaVisualizacoes = calcularMediaVisualizacoes(totalVideos);

        return new EstatisticasVideoResponse(totalVideos, totalFavoritos, mediaVisualizacoes);
    }

    private float calcularMediaVisualizacoes(Long totalVideos) {
        var videos = videoService.getAllVideos();
        var totalViews = videos.map(Video::getView).reduce(Long::sum).block();

        return totalVideos > 0 ? (float) totalViews / totalVideos : 0;
    }
}
