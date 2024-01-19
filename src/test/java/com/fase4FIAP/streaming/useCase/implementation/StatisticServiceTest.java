package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;
import com.fase4FIAP.streaming.domain.dto.response.VideoStatisticResponse;
import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IStatisticService;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import com.fase4FIAP.streaming.utils.VideoHelper;
import com.jayway.jsonpath.internal.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StatisticServiceTest {

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
        statisticService = new StatisticService(videoService, favoriteVideoService);
    }

    // TODO -  Resolver allowPreviewAverage para depois testar
//    @Test
//    void allowCalculateStatistic(){
//        fail("Teste não implementado.");
//    }


    @Test
    void allowCalculateTotalVideos(){
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();

        when(reactiveVideoRepository.findAll()).thenReturn(Flux.just(video1, video2, video3));

        var totalVideos = videoService.getAllVideos().count().block();

        assertThat(totalVideos).isEqualTo(3L);
        verify(reactiveVideoRepository, times(1)).findAll();
    }

    @Test
    void generateExceptionCalculateTotalVideos(){
        when(reactiveVideoRepository.findAll()).thenReturn(Flux.error(new RuntimeException("Simulando uma exceção")));

        assertThatThrownBy(() -> videoService.getAllVideos().count().block())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Simulando uma exceção");

        verify(reactiveVideoRepository, times(1)).findAll();
    }


    @Test
    void allowFavoritesCounter(){
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listOfFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);

        when(favoriteVideoRepository.findAll()).thenReturn(listOfFavoritesVideos);

        var totalVideos = favoriteVideoService.getAll();

        assertThat(totalVideos).hasSize(3);
        verify(favoriteVideoRepository, times(1)).findAll();
    }

    // TODO - Simular situação para testar
//    @Test
//    void allowPreviewAverage(){
//        var video1 = VideoHelper.createVideo();
//        var video2 = VideoHelper.createVideo();
//        var video3 = VideoHelper.createVideo();
//
//        when(reactiveVideoRepository.findAll()).thenReturn(Flux.just(video1, video2, video3));
//
//
//    }

    private <T> T invokePrivateMethod(Object target, String methodName, Object... args) {
        try {
            Class<?>[] parameterTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }

            Method method = target.getClass().getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);

            return (T) method.invoke(target, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke private method: " + methodName, e);
        }
    }


}
