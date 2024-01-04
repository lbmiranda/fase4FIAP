package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class VideoRepositoryTest {

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
    void allowSaveVideo(){
        var video = VideoHelper.createVideo();
        Mockito.when(videoRepository.save(any(Video.class))).thenReturn(video);

        var videoCreated = videoRepository.save(video);

        assertThat(videoCreated).isNotNull().isEqualTo(video);
        verify(videoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void allowDeleteVideo(){

    }
}
