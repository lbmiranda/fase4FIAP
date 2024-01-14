package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IRecommendationVideoService;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecommendationVideoServiceTest {

    private IRecommendationVideoService recommendationVideoService;
    private VideoService videoService;
    private FavoriteVideoService favoriteVideoService;

    AutoCloseable mock;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;
    @Mock
    private ReactiveVideoRepository reactiveVideoRepository;
    @Mock
    private  VideoRepository videoRepository;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        favoriteVideoService = new FavoriteVideoService(favoriteVideoRepository);
        videoService = new VideoService(reactiveVideoRepository, videoRepository, favoriteVideoService);
        recommendationVideoService = new RecommendationVideoService(videoService, favoriteVideoService);
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }

    @Test
    void allowRecommendVideos(){
        String userId = "987654";
        String videoId = "123456";
        var category = Category.ENTERTAINMENT;
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listOfFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);

        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfReactiveVideos = Arrays.asList(video1, video2, video3);

        when(favoriteVideoRepository.findByUserId(any(String.class))).thenReturn(listOfFavoritesVideos);
        when(reactiveVideoRepository.findById(any(String.class))).thenReturn(Mono.just(video1));
        when(reactiveVideoRepository.findByCategory(any(Category.class))).thenReturn(Flux.fromIterable(listOfReactiveVideos));

        var categoriesSort = recommendationVideoService.recommendVideos(userId);

        assertThat(categoriesSort).hasSize(3);
        assertThat(categoriesSort).isEqualTo(listOfReactiveVideos);
        verify(favoriteVideoRepository, times(2)).findByUserId(userId);
        verify(reactiveVideoRepository, times(listOfFavoritesVideos.size())).findById(videoId);
        verify(reactiveVideoRepository, times(1)).findByCategory(category);
    }

}
