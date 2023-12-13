package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.VideoModelo;
import com.fase4FIAP.streaming.dominio.VideoModeloRequest;
import com.fase4FIAP.streaming.dominio.VideoRepositorio;
import com.fase4FIAP.streaming.dominio.VideoUploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ServicoVideo implements StreamingVideo {

    private final VideoRepositorio videoRepositorio;

    public Mono<VideoUploadResult> uploadVideo(MultipartFile arquivo, VideoModeloRequest request) {
        return Mono.fromCallable(arquivo::getBytes)
                .map(videoData -> VideoModelo.of(videoData, request))
                .flatMap(videoRepositorio::save)
                .map(video -> new VideoUploadResult(true, video.getVideoId()))
                .defaultIfEmpty(new VideoUploadResult(false, null));
    }

    @Override
    public Mono<byte[]> getVideoContent(String videoId) {
        return videoRepositorio.findById(videoId).map(VideoModelo::getVideoData);
    }

    @Override
    public Flux<VideoModelo> getAllVideos() {
        return videoRepositorio.findAll();
    }
}
