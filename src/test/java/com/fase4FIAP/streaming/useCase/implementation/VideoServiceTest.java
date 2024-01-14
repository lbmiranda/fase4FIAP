package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import com.fase4FIAP.streaming.useCase.contract.IVideoService;
import com.fase4FIAP.streaming.utils.FavoriteVideoHelper;
import com.fase4FIAP.streaming.utils.VideoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class VideoServiceTest {

    final String videoId = "123ABC";
    final String userId = "987654";

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
        var video = VideoHelper.createVideo();
        var response = new VideoUploadResponse(true, video.getVideoId());

        MultipartFile mockMultipartFile = new MockMultipartFile(
                file,
                file + "txt",
                "text/plain",
                "Test file content".getBytes()
        );

        Mockito.when(reactiveVideoRepository.save(Mockito.any(Video.class))).thenReturn(Mono.just(video));

        StepVerifier.create(videoService.uploadVideo(mockMultipartFile, reactiveVideo))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void allowGetVideoContent(){
        var video = VideoHelper.createVideo();
        when(reactiveVideoRepository.findById(any(String.class))).thenReturn(Mono.just(video));
//        when(reactiveVideoRepository.save(any(Video.class))).thenReturn(Mono.just(video));

        var videoFiltered = videoService.getVideoContent(videoId);

        assertThat(videoFiltered).isNotNull();
        verify(reactiveVideoRepository, times(1)).findById(videoId);
//        verify(reactiveVideoRepository, times(1)).save(video);
    }

    @Test
    void generateNotFoundExceptionFindById(){
        when(reactiveVideoRepository.findById(any(String.class)))
            .thenAnswer(invocation -> Mono.error(new NotFoundException("Vídeo não encontrado com ID: " + videoId)));

        var result = videoService.getVideoContent(videoId);

        StepVerifier.create(result)
            .expectError(NotFoundException.class)
            .verify();

        verify(reactiveVideoRepository, times(1)).findById(videoId);
    }

    @Test
    void allowGetAllVideos(){
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfVideos = Arrays.asList(video1, video2, video3);
        when(reactiveVideoRepository.findAll()).thenReturn(Flux.fromIterable(listOfVideos));

        var listOfVideosFiltered = videoService.getAllVideos();

        assertEquals(listOfVideos, listOfVideosFiltered.collectList().block());
    }

    @Test
    void allowGetAllVideosPaginate(){
        int page = 1;
        int size = 10;
        var pageable = PageRequest.of(page, size, Sort.by("publicationDate").descending());
        Page<Video> listOfVideos = new PageImpl<>(Arrays.asList(
                VideoHelper.createVideo(),
                VideoHelper.createVideo(),
                VideoHelper.createVideo()
        ));

        when(videoRepository.findAll(any(Pageable.class))).thenReturn(listOfVideos);

        var result = videoService.getAllVideosPaginate(page, size);

        assertThat(result).hasSize(3);
        assertThat(result.getContent()).asList().allSatisfy(
                video -> {
                    assertThat(video).isNotNull().isInstanceOf(Video.class);
                });
        verify(videoRepository, times(1)).findAll(pageable);
    }

    @Test
    void allowGetById() {
        var video = VideoHelper.createVideo();
        when(reactiveVideoRepository.findById(any(String.class))).thenReturn(Mono.just(video));

        var videoFiltered = videoService.getById(videoId);

        assertThat(videoFiltered).isEqualTo(video);
        verify(reactiveVideoRepository, times(1)).findById(videoId);
    }

    @Test
    void allowDelete(){
        when(reactiveVideoRepository.deleteById(any(String.class))).thenReturn(Mono.empty());

        videoService.delete(videoId);

        verify(reactiveVideoRepository, times(1)).deleteById(videoId);
    }

    @Test
    void allowFindByCategoryAndNotFavoritedByUser(){
        var category = Category.ENTERTAINMENT;
        var favoriteVideo1 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo2 = FavoriteVideoHelper.createFavoriteVideo();
        var favoriteVideo3 = FavoriteVideoHelper.createFavoriteVideo();
        var listOfFavoritesVideos = Arrays.asList(favoriteVideo1, favoriteVideo2, favoriteVideo3);

        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfVideos = Arrays.asList(video1, video2, video3);

        when(favoriteVideoRepository.findByUserId(any(String.class))).thenReturn(listOfFavoritesVideos);
        when(reactiveVideoRepository.findByCategory(any(Category.class))).thenReturn(Flux.fromIterable(listOfVideos));

        var filter = videoService.findByCategoryAndNotFavoritedByUser(category, userId);

        assertThat(filter).isNotNull();
        verify(favoriteVideoRepository, times(1)).findByUserId(userId);
        verify(reactiveVideoRepository, times(1)).findByCategory(category);
    }

    @Test
    void allowDeleteVideo(){
        var video = VideoHelper.createVideo();
        when(reactiveVideoRepository.findById(any(String.class))).thenReturn(Mono.just(video));
        when(reactiveVideoRepository.deleteById(any(String.class))).thenReturn(Mono.empty());

        var deleteResult = videoService.deleteVideo(videoId);

        StepVerifier.create(deleteResult)
                .expectNextCount(1)
                .verifyComplete();

        verify(reactiveVideoRepository, times(1)).findById(videoId);
        verify(reactiveVideoRepository, times(1)).deleteById(videoId);
    }

    @Test
    void allowFindVideoByTitle(){
        String title = "Breaking Bad";
        var video1 = VideoHelper.createVideo();
        var video2 = VideoHelper.createVideo();
        var video3 = VideoHelper.createVideo();
        var listOfVideos = Arrays.asList(video1, video2, video3);
        when(reactiveVideoRepository.findByTitleContainingIgnoreCase(any(String.class))).thenReturn(Flux.fromIterable(listOfVideos));

        var listFiltered = videoService.findVideoByTitle(title);

        assertThat(listFiltered).isNotNull();
        verify(reactiveVideoRepository, times(1)).findByTitleContainingIgnoreCase(title);
    }



}
