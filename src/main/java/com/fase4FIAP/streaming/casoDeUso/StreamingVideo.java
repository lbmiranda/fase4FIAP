package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.VideoModelo;
import com.fase4FIAP.streaming.dominio.VideoModeloRequest;
import com.fase4FIAP.streaming.dominio.VideoUploadResult;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StreamingVideo {

    Mono<VideoUploadResult> uploadVideo(MultipartFile arquivo, VideoModeloRequest request);

    Mono<byte[]> getVideoContent(String videoId);

    Flux<VideoModelo> getAllVideos();

}
