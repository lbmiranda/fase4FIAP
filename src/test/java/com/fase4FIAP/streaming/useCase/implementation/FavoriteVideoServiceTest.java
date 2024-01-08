package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.AlreadyFavoritedException;
import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
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
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FavoriteVideoServiceTest {

    final String id = "123ABC";
    final String userId = "987654";
    final String videoId = "123456";

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
    void allowFavoriteVideo(){
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

    @Test
    void generateAlreadyFavoritedExceptionFavoriteVideo() {
        var favoriteVideo = new FavoriteVideoRequest("987654", "123456", LocalDate.now());

        when(favoriteVideoRepository.findByUserIdAndVideoId(any(String.class), any(String.class))).thenReturn(Optional.of(FavoriteVideo.of(favoriteVideo)));


        assertThatThrownBy(() -> favoriteVideoService.favorite(favoriteVideo))
                .isInstanceOf(AlreadyFavoritedException.class)
                .hasMessage("O vídeo já está favoritado pelo usuário.");
        verify(favoriteVideoRepository, times(1)).findByUserIdAndVideoId(userId, videoId);
    }

    @Test
    void allowFindByUserFavoriteVideo(){
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listsFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        when(favoriteVideoRepository.findByUserId(any(String.class))).thenReturn(listsFavoritesVideos);

        var listFilter = favoriteVideoService.getFavoritesByUser(userId);
        assertThat(listFilter).hasSize(3);
        verify(favoriteVideoRepository, times(1)).findByUserId(userId);
    }

    @Test
    void allowGetAllFavoritesVideos(){
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listsFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);
        when(favoriteVideoRepository.findAll()).thenReturn(listsFavoritesVideos);

        var listFilter = favoriteVideoService.getAll();
        assertThat(listFilter).hasSize(3);
        verify(favoriteVideoRepository, times(1)).findAll();
    }

    @Test
    void allowDeleteByIdFavoriteVideo(){
        var favoriteVideo = FavoriteVideoHelper.createFavoriteVideo();
        when(favoriteVideoRepository.findById(id)).thenReturn(Optional.of(favoriteVideo));
        doNothing().when(favoriteVideoRepository).deleteById(id);

        favoriteVideoService.delete(id);

        verify(favoriteVideoRepository, times(1)).findById(any(String.class));
        verify(favoriteVideoRepository, times(1)).deleteById(any(String.class));
    }

    @Test
    void generateNotFoundExceptionForDeleteById(){
        when(favoriteVideoRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> favoriteVideoService.delete(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Video favorito não encontrado com id: " + id);
        verify(favoriteVideoRepository, times(1)).findById(id);
    }













}
