package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IRecommendationVideoService;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RecommendationVideoServiceTest {

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
        var category = Category.ENTERTAINMENT;
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfVideos = Arrays.asList(video1, video2, video3);

        var categoriesSort = recommendationVideoService.recommendVideos(userId);

        assertThat(categoriesSort).hasSize(3);
        assertThat(categoriesSort).isEqualTo(listOfVideos);
        verify(favoriteVideoRepository, times(1)).findByUserId(userId);
        verify(reactiveVideoRepository, times(1)).findById(userId);
        verify(reactiveVideoRepository, times(1)).findByCategory(category);

    }

}
