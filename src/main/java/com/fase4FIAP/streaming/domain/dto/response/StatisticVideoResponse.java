package com.fase4FIAP.streaming.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticVideoResponse {

    private long totalVideos;
    private long totalFavorites;
    private float previewAverage;
}
