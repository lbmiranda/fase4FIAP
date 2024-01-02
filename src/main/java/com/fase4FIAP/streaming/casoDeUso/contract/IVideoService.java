package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoDeleteResponse;
import com.fase4FIAP.streaming.dominio.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.dominio.model.Video;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IVideoService {

    Mono<VideoUploadResponse> uploadVideo(MultipartFile arquivo, VideoRequest request);

    Mono<byte[]> getVideoContent(String videoId);

    Flux<Video> getAllVideos();

    Video getById(String id);

    public Mono<VideoDeleteResponse> deleteVideo(String videoId);

    Flux<Video> buscaVideoPorTitulo(String query);

}
