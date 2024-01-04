package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FavoriteVideoRepositoryTest {

    final String id = "123ABC";
    final String userID = "987654";

    final String videoID = "123456";

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

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
    void allowSaveFavoriteVideo() {
        var favoriteVideo = FavoriteVideoHelper.createFavoriteVideo();
        Mockito.when(favoriteVideoRepository.save(any(FavoriteVideo.class))).thenReturn(favoriteVideo);

        var favoriteVideoCreated = favoriteVideoRepository.save(favoriteVideo);

        assertThat(favoriteVideoCreated).isNotNull().isEqualTo(favoriteVideo);
        verify(favoriteVideoRepository, times(1)).save(any(FavoriteVideo.class));
    }

    @Test
    void allowFindByUserId() {
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        Mockito.when(favoriteVideoRepository.findByUserId(any(String.class))).thenReturn(listFavoritesVideos);

        var listFilter = favoriteVideoRepository.findByUserId(userID);

        assertThat(listFilter).hasSize(3).containsExactlyInAnyOrder(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        verify(favoriteVideoRepository, times(1)).findByUserId(userID);
    }

    @Test
    void allowFindAllVideos() {
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        Mockito.when(favoriteVideoRepository.findAll()).thenReturn(listFavoritesVideos);

        var listFilter = favoriteVideoRepository.findAll();

        assertThat(listFilter).hasSize(3).containsExactlyInAnyOrder(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        verify(favoriteVideoRepository, times(1)).findAll();
    }

    @Test
    void allowDeleteByIdVideo() {
        doNothing().when(favoriteVideoRepository).deleteById(any(String.class));

        favoriteVideoRepository.deleteById(id);

        verify(favoriteVideoRepository, times(1)).deleteById(any(String.class));
    }

    @Test
    void allowFindByUserIdAndVideoIdVideo() {
        var favoriteVideo = FavoriteVideoHelper.createFavoriteVideo();
        Mockito.when(favoriteVideoRepository.findByUserIdAndVideoId(userID, videoID)).thenReturn(Optional.of(favoriteVideo));

        var favoriteVideoFilter = favoriteVideoRepository.findByUserIdAndVideoId(userID, videoID);

        assertThat(favoriteVideoFilter).isPresent().containsSame(favoriteVideo);
        favoriteVideoFilter.ifPresent(search -> {
            assertThat(search.getId()).isEqualTo(favoriteVideo.getId());
            assertThat(search.getVideoId()).isEqualTo(favoriteVideo.getVideoId());
            assertThat(search.getUserId()).isEqualTo(favoriteVideo.getUserId());
            assertThat(search.getCreationDate()).isEqualTo(favoriteVideo.getCreationDate());
        });

        verify(favoriteVideoRepository, times(1)).findByUserIdAndVideoId(any(String.class), any(String.class));
    }

    @Test
    void allowFindById(){
        var favoriteVideo = FavoriteVideoHelper.createFavoriteVideo();
        Mockito.when(favoriteVideoRepository.findById(any(String.class))).thenReturn(Optional.of(favoriteVideo));

        var favoriteVideoFilter = favoriteVideoRepository.findById(id);

        assertThat(favoriteVideoFilter).isPresent().containsSame(favoriteVideo);
        favoriteVideoFilter.ifPresent(search -> {
            assertThat(search.getId()).isEqualTo(favoriteVideo.getId());
            assertThat(search.getVideoId()).isEqualTo(favoriteVideo.getVideoId());
            assertThat(search.getUserId()).isEqualTo(favoriteVideo.getUserId());
            assertThat(search.getCreationDate()).isEqualTo(favoriteVideo.getCreationDate());
        });

        verify(favoriteVideoRepository, times(1)).findById(any(String.class));
    }

}
