package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.dominio.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

public interface IVideoService {

    Mono<VideoUploadResponse> uploadVideo(MultipartFile arquivo, VideoRequest request);

    Mono<byte[]> getVideoContent(String videoId);

    Flux<Video> getAllVideos();

    Page<Video> getAllVideosPaginado(int page, int size);

    Video getById(String id);

    void delete(String id);
}
