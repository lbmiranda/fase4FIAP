package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ReactiveVideoRepositoryTest {

    final String videoId = "123ABC";
    @Mock
    private ReactiveVideoRepository reactiveVideoRepository;

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
    void allowSaveReactiveVideo(){
        var reactiveVideo = VideoHelper.createVideo();
        Mockito.when(reactiveVideoRepository.save(any(Video.class))).thenReturn(Mono.just(reactiveVideo));

        var videoCreated = reactiveVideoRepository.save(reactiveVideo);
        
        assertEquals(reactiveVideo, videoCreated.block());
        verify(reactiveVideoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void allowFindByIdReactiveVideo(){
        var reactiveVideo = VideoHelper.createVideo();
        Mockito.when(reactiveVideoRepository.findById(any(String.class))).thenReturn(Mono.just(reactiveVideo));

        var reactiveVideoFilter = reactiveVideoRepository.findById(videoId);

        StepVerifier.create(reactiveVideoFilter)
                .expectNext(reactiveVideo)
                .expectComplete()
                .verify();

        verify(reactiveVideoRepository, times(1)).findById(any(String.class));
    }
}
