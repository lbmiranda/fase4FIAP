package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.useCase.contract.IStatisticService;
import com.fase4FIAP.streaming.domain.dto.response.VideoStatisticResponse;
import com.fase4FIAP.streaming.domain.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticService implements IStatisticService {

    private final VideoService videoService;
    private final FavoriteVideoService favoriteVideoService;

    @Override
    public VideoStatisticResponse calculateStatistics() {
        var totalVideos = videosCounter();
        var totalFavorites = favoritesCounter();
        var previewAverage = calculateAverageViews(totalVideos);

        return new VideoStatisticResponse(totalVideos, totalFavorites, previewAverage);
    }

    private long videosCounter() {
        return safelyBlock(videoService.getAllVideos().count());
    }

    private int favoritesCounter() {
        return safelyGetSize(favoriteVideoService.getAll());
    }

    private float calculateAverageViews(long totalVideos) {
        if (totalVideos == 0) {
            return 0;
        }

        var totalViews = getFullViews();

        return (float) totalViews / totalVideos;
    }

    private long getFullViews() {
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
