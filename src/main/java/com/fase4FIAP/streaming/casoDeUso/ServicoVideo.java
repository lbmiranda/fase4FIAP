package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Override
    public Mono<VideoDeleteResult> deleteVideo(String videoId) {

        return videoRepositorio.findById(videoId)
                .flatMap(existingVideo -> videoRepositorio.deleteById(videoId)
                        .thenReturn(VideoDeleteResult.success()))
                .switchIfEmpty(Mono.just(VideoDeleteResult.failure()));
    }

    @Override
    public Flux<VideoModelo> buscaVideoPorTitulo (String query) {
        return videoRepositorio.findByTituloContainingIgnoreCase(query);
    }


}
