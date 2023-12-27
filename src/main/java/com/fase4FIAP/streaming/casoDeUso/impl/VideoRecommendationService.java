package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.casoDeUso.contract.IVideoRecommendationService;
import com.fase4FIAP.streaming.dominio.enums.Categoria;
import com.fase4FIAP.streaming.dominio.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoRecommendationService implements IVideoRecommendationService {

    private final VideoService videoService;
    private final VideoFavoritoService videoFavoritoService;

    @Override
    public List<Video> recommendVideos(String usuarioId) {
        Map<Categoria, Long> categoriasFavoritas = getCategoriasFavoritas(usuarioId);

        var categoriasOrdenadas = categoriasFavoritas.entrySet()
                .stream()
                .sorted(Map.Entry.<Categoria, Long>comparingByValue().reversed())
                .toList();

        return recomendarPorCategorias(categoriasOrdenadas, usuarioId);
    }

    private Map<Categoria, Long> getCategoriasFavoritas(String usuarioId) {
        var favoritos = videoFavoritoService.getFavoritosPorUsuario(usuarioId);
        return favoritos.stream()
                .map(favorito -> videoService.getById(favorito.getVideoId()))
                .collect(Collectors.groupingBy(Video::getCategoria, Collectors.counting()));
    }

    private List<Video> recomendarPorCategorias(List<Map.Entry<Categoria, Long>> categoriasOrdenadas, String usuarioId) {
        var recommendations = new ArrayList<Video>();
        for (Map.Entry<Categoria, Long> categoria : categoriasOrdenadas) {
            var videosNessaCategoria = videoService.findByCategoriaAndNotFavoritedByUser(categoria.getKey(), usuarioId);
            recommendations.addAll(videosNessaCategoria);
        }
        return recommendations;
    }
}
