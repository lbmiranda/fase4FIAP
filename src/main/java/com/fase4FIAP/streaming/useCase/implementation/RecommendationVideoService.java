package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.useCase.contract.IRecommendationVideoService;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationVideoService implements IRecommendationVideoService {

    private final VideoService videoService;
    private final FavoriteVideoService favoriteVideoService;

    @Override
    public List<Video> recommendVideos(String userId) {
        var categoriesFavorites = getCategoriesFavorites(userId);



        var categoriesSort = categoriesFavorites.entrySet()
                .stream()
                .sorted(Map.Entry.<Category, Long>comparingByValue().reversed())
                .toList();

        return recommendByCategory(categoriesSort, userId);
    }

    private Map<Category, Long> getCategoriesFavorites(String userId) {
        var favorites = favoriteVideoService.getFavoritesByUser(userId);
        return favorites.stream()
                .map(favorite -> videoService.getById(favorite.getVideoId()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Video::getCategory, Collectors.counting()));
    }

    private List<Video> recommendByCategory(List<Map.Entry<Category, Long>> categoriesSort, String userId) {
        var recommendations = new ArrayList<Video>();
        for (Map.Entry<Category, Long> category : categoriesSort) {
            var videosByCategory = videoService.findByCategoryAndNotFavoritedByUser(category.getKey(), userId);
            recommendations.addAll(videosByCategory);
        }
        return recommendations;
    }
}
