package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IFavoriteVideoService;
import com.fase4FIAP.streaming.useCase.contract.IVideoService;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

public class VideoServiceTest {

    private IVideoService videoService;

    private FavoriteVideoService favoriteVideoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ReactiveVideoRepository reactiveVideoRepository;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;



    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        favoriteVideoService = new FavoriteVideoService(favoriteVideoRepository);
        videoService = new VideoService(reactiveVideoRepository, videoRepository, favoriteVideoService);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void allowUploadVideo(){
        String file = "breaking_bad";
        var reactiveVideo = new VideoRequest("Breaking Bad", "Piloto", file, "2000-01-01", Category.ENTERTAINMENT.getCategoryName(), "");


    }


}
