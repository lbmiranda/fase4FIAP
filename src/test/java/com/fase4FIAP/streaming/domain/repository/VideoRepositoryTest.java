package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VideoRepositoryTest {

    final String videoId = "123ABC";
    @Mock
    private VideoRepository videoRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }
    
    @Test
    void allowFindAllVideos() {
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listVideos = Arrays.asList(video1, video2, video3);
        when(videoRepository.findAll()).thenReturn(listVideos);

        var listFilter = videoRepository.findAll();

        assertThat(listFilter).hasSize(3).containsExactlyInAnyOrder(video1, video2, video3);
        verify(videoRepository, times(1)).findAll();
    }






}
