package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.DeleteVideoResponse;
import com.fase4FIAP.streaming.domain.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IVideoService {

    Mono<VideoUploadResponse> uploadVideo(MultipartFile file, VideoRequest request);

    Mono<byte[]> getVideoContent(String videoId);

    Flux<Video> getAllVideos();

    Page<Video> getAllVideosPaginate(int page, int size);

    Video getById(String id);

    public Mono<DeleteVideoResponse> deleteVideo(String videoId);

    Flux<Video> findVideoByTitle(String query);

    void delete(String id);

    List<Video> findByCategoryAndNotFavoritedByUser(Category category, String userId);
}
