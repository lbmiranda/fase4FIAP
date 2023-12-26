package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.VideoDeleteResult;
import com.fase4FIAP.streaming.dominio.VideoModelo;
import com.fase4FIAP.streaming.dominio.VideoModeloRequest;
import com.fase4FIAP.streaming.dominio.VideoUploadResult;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StreamingVideo {

    Mono<VideoUploadResult> uploadVideo(MultipartFile arquivo, VideoModeloRequest request);

    Mono<byte[]> getVideoContent(String videoId);

    Flux<VideoModelo> getAllVideos();

    Mono<VideoDeleteResult> deleteVideo(String videoId);

    Flux<VideoModelo> buscaVideoPorTitulo(String query);


}
