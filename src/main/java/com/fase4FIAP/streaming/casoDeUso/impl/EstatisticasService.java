package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.casoDeUso.contract.IEstatisticasService;
import com.fase4FIAP.streaming.dominio.dto.response.EstatisticasVideoResponse;
import com.fase4FIAP.streaming.dominio.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstatisticasService implements IEstatisticasService {

    private final VideoService videoService;
    private final VideoFavoritoService videoFavoritoService;

    @Override
    public EstatisticasVideoResponse calcularEstatisticas() {
        var totalVideos = contarTotalVideos();
        var totalFavoritos = contarTotalFavoritos();
        var mediaVisualizacoes = calcularMediaVisualizacoes(totalVideos);

        return new EstatisticasVideoResponse(totalVideos, totalFavoritos, mediaVisualizacoes);
    }

    private long contarTotalVideos() {
        return safelyBlock(videoService.getAllVideos().count());
    }

    private int contarTotalFavoritos() {
        return safelyGetSize(videoFavoritoService.getAll());
    }

    private float calcularMediaVisualizacoes(long totalVideos) {
        if (totalVideos == 0) {
            return 0;
        }

        var totalViews = obterTotalVisualizacoes();

        return (float) totalViews / totalVideos;
    }

    private long obterTotalVisualizacoes() {
        return Optional.of(safelyBlock(videoService.getAllVideos()
                        .map(Video::getView)
                        .reduce(Long::sum)))
                .orElse(0L);
    }

    private long safelyBlock(Mono<Long> mono) {
        try {
            return Optional.ofNullable(mono.block()).orElse(0L);
        } catch (Exception e) {
            return 0;
        }
    }

    private int safelyGetSize(List<?> list) {
        return list != null ? list.size() : 0;
    }
}
