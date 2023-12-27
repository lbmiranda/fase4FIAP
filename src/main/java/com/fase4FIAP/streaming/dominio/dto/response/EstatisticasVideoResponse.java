package com.fase4FIAP.streaming.dominio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstatisticasVideoResponse {

    private long totalVideos;
    private long totalFavoritos;
    private float mediaVisualizacoes;
}
