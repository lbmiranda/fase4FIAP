package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IFavoriteVideoService;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FavoriteVideoServiceTest {

    final String id = "123ABC";

    private IFavoriteVideoService favoriteVideoService;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        favoriteVideoService = new FavoriteVideoService(favoriteVideoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    
    @Test
    void allowFavorite(){
        var favoriteVideo = new FavoriteVideoRequest("123ABC", "789CDD", LocalDate.now());
        Mockito.when(favoriteVideoRepository.save(any(FavoriteVideo.class))).thenAnswer(i -> i.getArgument(0));

        var favoriteVideoResponse = favoriteVideoService.favorite(favoriteVideo);
        var favoriteVideoCreated = FavoriteVideo.ofResponse(favoriteVideoResponse);

        assertThat(favoriteVideoCreated).isInstanceOf(FavoriteVideo.class).isNotNull();
        assertThat(favoriteVideoCreated.getUserId()).isEqualTo(favoriteVideo.getUserId());
        assertThat(favoriteVideoCreated.getVideoId()).isEqualTo(favoriteVideo.getVideoId());
        assertThat(favoriteVideoCreated.getCreationDate()).isEqualTo(favoriteVideo.getCreationDate());
        verify(favoriteVideoRepository, times(1)).save(favoriteVideoCreated);
    }
}
