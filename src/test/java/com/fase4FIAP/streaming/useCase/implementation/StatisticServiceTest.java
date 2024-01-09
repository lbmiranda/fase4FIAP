package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IStatisticService;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class StatisticServiceTest {

    private IStatisticService statisticService;
    private VideoService videoService;
    private FavoriteVideoService favoriteVideoService;

    AutoCloseable mock;

    @Mock
    private ReactiveVideoRepository reactiveVideoRepository;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

    @Mock
    private VideoRepository videoRepository;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        favoriteVideoService = new FavoriteVideoService(favoriteVideoRepository);
        videoService = new VideoService(reactiveVideoRepository, videoRepository, favoriteVideoService);
    }
    @Test
    void allowCalculateTotalVideos(){
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();

        reactiveVideoRepository.save(video1);
        reactiveVideoRepository.save(video2);
        reactiveVideoRepository.save(video3);
        var totalVideos = videoService.getAllVideos().count();

        assertThat(totalVideos).isEqualTo(3L);
        verify(reactiveVideoRepository, times(3)).save();
        verify(reactiveVideoRepository, times(1)).findAll();

    }

//    @Test
//    void generateExceptionCalculateTotalVideos(){
//
//    }
}
