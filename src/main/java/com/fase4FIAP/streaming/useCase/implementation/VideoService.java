package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.useCase.contract.IVideoService;
import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.DeleteVideoResponse;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;
import com.fase4FIAP.streaming.domain.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import com.fase4FIAP.streaming.domain.repository.ReactiveVideoRepository;
import com.fase4FIAP.streaming.domain.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {

    private final ReactiveVideoRepository reactiveVideoRepository;
    private final VideoRepository videoRepository;
    private final FavoriteVideoService favoriteVideoService;

    public Mono<VideoUploadResponse> uploadVideo(MultipartFile file, VideoRequest request) {
        return Mono.fromCallable(file::getBytes)
                .map(videoData -> Video.of(videoData, request))
                .flatMap(reactiveVideoRepository::save)
                .map(video -> new VideoUploadResponse(true, video.getVideoId()))
                .defaultIfEmpty(new VideoUploadResponse(false, null));
    }

    @Override
    public Mono<byte[]> getVideoContent(String videoId) {
        return reactiveVideoRepository.findById(videoId)
                .flatMap(video -> {
                    video.incrementView();
                    return reactiveVideoRepository.save(video)
                            .thenReturn(video);
                })
                .map(Video::getVideoData)
                .switchIfEmpty(Mono.error(new NotFoundException("Vídeo não encontrado com ID: " + videoId)));
    }

    @Override
    public Flux<Video> getAllVideos() {
        return reactiveVideoRepository.findAll();
    }

    @Override
    public Page<Video> getAllVideosPaginate(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("publicationDate").descending());
        return videoRepository.findAll(pageable);
    }

    @Override
    public Video getById(String id) {
        return reactiveVideoRepository.findById(id).block();
    }

    @Override
    public void delete(String id) {
        reactiveVideoRepository.deleteById(id).subscribe(
                null,
                error -> System.err.println("Erro ao deletar vídeo: " + error),
                () -> System.out.println("Vídeo deletado com sucesso: " + id));
    }

    public List<Video> findByCategoryAndNotFavoritedByUser(Category category, String userId) {
        var idsFavorites = favoriteVideoService.getFavoritesByUser(userId)
                .stream()
                .map(FavoriteVideoResponse::getVideoId)
                .toList();

        return reactiveVideoRepository.findByCategory(category)
                .toStream()
                .filter(video -> !idsFavorites.contains(video.getVideoId()))
                .toList();
    }

    @Override
    public Mono<DeleteVideoResponse> deleteVideo(String videoId) {

        return reactiveVideoRepository.findById(videoId)
                .flatMap(existingVideo -> reactiveVideoRepository.deleteById(videoId)
                        .thenReturn(DeleteVideoResponse.success()))
                .switchIfEmpty(Mono.just(DeleteVideoResponse.error()));
    }


    @Override
    public Flux<Video> findVideoByTitle(String query) {
        return reactiveVideoRepository.findByTitleContainingIgnoreCase(query);
    }


}
