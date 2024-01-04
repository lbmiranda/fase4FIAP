package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.utils.UserHelper;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
        doNothing().when(videoRepository).deleteById(any(String.class));

        videoRepository.deleteById(videoId);

        verify(videoRepository, times(1)).deleteById(any(String.class));
    }

    @Test
    void allowFilterVideo(){
        var video = VideoHelper.createVideo();
        when(videoRepository.findById(any(String.class))).thenReturn(Optional.of(video));

        var videoFilter = videoRepository.findById(videoId);

        assertThat(videoFilter).isPresent().containsSame(video);
        videoFilter.ifPresent(search -> {
            assertThat(search.getVideoId()).isEqualTo(video.getVideoId());
            assertThat(search.getTitle()).isEqualTo(video.getTitle());
            assertThat(search.getDescription()).isEqualTo(video.getDescription());
            assertThat(search.getVideoData()).isEqualTo(video.getVideoData());
            assertThat(search.getFileName()).isEqualTo(video.getFileName());
            assertThat(search.getPublicationDate()).isEqualTo(video.getPublicationDate());
            assertThat(search.getCategory()).isEqualTo(video.getCategory());
            assertThat(search.getView()).isEqualTo(video.getView());

        });

        verify(videoRepository, times(1)).findById(any(String.class));
    }

    @Test
    void allowToListUsers() {
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
