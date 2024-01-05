package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void allowFindAllReactiveVideo(){
        var reactiveVideo1 = VideoHelper.createVideo();
        var reactiveVideo2 = VideoHelper.createVideo();
        var reactiveVideo3 = VideoHelper.createVideo();
        var listReactiveVideos = Arrays.asList(reactiveVideo1, reactiveVideo2, reactiveVideo3);
        Mockito.when(reactiveVideoRepository.findAll()).thenReturn(Flux.fromIterable(listReactiveVideos));

        var listFilter = reactiveVideoRepository.findAll();
        
        assertEquals(listReactiveVideos, listFilter.collectList().block());
        verify(reactiveVideoRepository, times(1)).findAll();
    }

    @Test
    void allowDeleteByIdReactiveVideo(){
        Mockito.when(reactiveVideoRepository.deleteById(videoId)).thenReturn(Mono.empty());
        var deleteResult = reactiveVideoRepository.deleteById(videoId);

        StepVerifier.create(deleteResult)
                .expectComplete()
                .verify();

        Mockito.verify(reactiveVideoRepository, times(1)).deleteById(videoId);
    }

    @Test
    void allowFindByCategoryReactiveVideo(){
        var category = Category.ENTERTAINMENT;
        var reactiveVideo1 = VideoHelper.createVideo();
        var reactiveVideo2 = VideoHelper.createVideo();
        var reactiveVideo3 = VideoHelper.createVideo();
        var listReactiveVideos = Arrays.asList(reactiveVideo1, reactiveVideo2, reactiveVideo3);
        when(reactiveVideoRepository.findByCategory(any(Category.class))).thenReturn(Flux.fromIterable(listReactiveVideos));

        var listFilter = reactiveVideoRepository.findByCategory(category);

        assertEquals(listReactiveVideos, listFilter.collectList().block());
        verify(reactiveVideoRepository, times(1)).findByCategory(category);
    }

    @Test
    void allowFindByTitleContainingIgnoreCaseReactiveVideo(){
        String title = "BREAKING BAD";
        var reactiveVideo1 = VideoHelper.createVideo();
        var reactiveVideo2 = VideoHelper.createVideo();
        var reactiveVideo3 = VideoHelper.createVideo();
        var listReactiveVideos = Arrays.asList(reactiveVideo1, reactiveVideo2, reactiveVideo3);
        when(reactiveVideoRepository.findByTitleContainingIgnoreCase(any(String.class))).thenReturn(Flux.fromIterable(listReactiveVideos));

        var listFilter = reactiveVideoRepository.findByTitleContainingIgnoreCase(title);

        assertEquals(listReactiveVideos, listFilter.collectList().block());
        verify(reactiveVideoRepository, times(1)).findByTitleContainingIgnoreCase(title);
    }


}
