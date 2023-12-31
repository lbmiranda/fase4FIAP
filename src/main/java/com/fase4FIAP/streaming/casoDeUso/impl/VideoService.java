package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.aplicacao.exceptions.NotFoundException;
import com.fase4FIAP.streaming.casoDeUso.contract.IVideoService;
import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;
import com.fase4FIAP.streaming.dominio.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.dominio.enums.Categoria;
import com.fase4FIAP.streaming.dominio.model.Video;
import com.fase4FIAP.streaming.dominio.repository.VideoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {

    private final VideoRepositorio videoRepositorio;
    private final VideoFavoritoService videoFavoritoService;

    public Mono<VideoUploadResponse> uploadVideo(MultipartFile arquivo, VideoRequest request) {
        return Mono.fromCallable(arquivo::getBytes)
                .map(videoData -> Video.of(videoData, request))
                .flatMap(videoRepositorio::save)
                .map(video -> new VideoUploadResponse(true, video.getVideoId()))
                .defaultIfEmpty(new VideoUploadResponse(false, null));
    }

    @Override
    public Mono<byte[]> getVideoContent(String videoId) {
        return videoRepositorio.findById(videoId)
                .flatMap(video -> {
                    video.incrementaView();
                    return videoRepositorio.save(video)
                            .thenReturn(video);
                })
                .map(Video::getVideoData)
                .switchIfEmpty(Mono.error(new NotFoundException("Vídeo não encontrado com ID: " + videoId)));
    }

    @Override
    public Flux<Video> getAllVideos() {
        return videoRepositorio.findAll();
    }

    @Override
    public Video getById(String id) {
        return videoRepositorio.findById(id).block();
    }

    @Override
    public void delete(String id) {
        videoRepositorio.deleteById(id).subscribe(
                null,
                error -> System.err.println("Erro ao deletar vídeo: " + error),
                () -> System.out.println("Vídeo deletado com sucesso: " + id));
    }

    public List<Video> findByCategoriaAndNotFavoritedByUser(Categoria categoria, String usuarioId) {
        var idsFavoritos = videoFavoritoService.getFavoritosPorUsuario(usuarioId)
                .stream()
                .map(VideoFavoritoResponse::getVideoId)
                .toList();

        return videoRepositorio.findByCategoria(categoria)
                .toStream()
                .filter(video -> !idsFavoritos.contains(video.getVideoId()))
                .toList();
    }
}
