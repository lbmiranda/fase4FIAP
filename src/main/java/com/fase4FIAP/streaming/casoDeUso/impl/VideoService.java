package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.aplicacao.exceptions.NotFoundException;
import com.fase4FIAP.streaming.casoDeUso.contract.IVideoService;
import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.dto.response.VideoDeleteResponse;
import com.fase4FIAP.streaming.dominio.dto.response.VideoFavoritoResponse;
import com.fase4FIAP.streaming.dominio.dto.response.VideoUploadResponse;
import com.fase4FIAP.streaming.dominio.enums.Categoria;
import com.fase4FIAP.streaming.dominio.model.Video;
import com.fase4FIAP.streaming.dominio.repository.VideoReativoRepositorio;
import com.fase4FIAP.streaming.dominio.repository.VideoRepositorio;
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

    private final VideoReativoRepositorio videoReativoRepositorio;
    private final VideoRepositorio videoRepositorio;
    private final VideoFavoritoService videoFavoritoService;

    public Mono<VideoUploadResponse> uploadVideo(MultipartFile arquivo, VideoRequest request) {
        return Mono.fromCallable(arquivo::getBytes)
                .map(videoData -> Video.of(videoData, request))
                .flatMap(videoReativoRepositorio::save)
                .map(video -> new VideoUploadResponse(true, video.getVideoId()))
                .defaultIfEmpty(new VideoUploadResponse(false, null));
    }

    @Override
    public Mono<byte[]> getVideoContent(String videoId) {
        return videoReativoRepositorio.findById(videoId)
                .flatMap(video -> {
                    video.incrementaView();
                    return videoReativoRepositorio.save(video)
                            .thenReturn(video);
                })
                .map(Video::getVideoData)
                .switchIfEmpty(Mono.error(new NotFoundException("Vídeo não encontrado com ID: " + videoId)));
    }

    @Override
    public Flux<Video> getAllVideos() {
        return videoReativoRepositorio.findAll();
    }

    @Override
    public Page<Video> getAllVideosPaginado(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("dataPublicacao").descending());
        return videoRepositorio.findAll(pageable);
    }

    @Override
    public Video getById(String id) {
        return videoReativoRepositorio.findById(id).block();
    }

    @Override
    public void delete(String id) {
        videoReativoRepositorio.deleteById(id).subscribe(
                null,
                error -> System.err.println("Erro ao deletar vídeo: " + error),
                () -> System.out.println("Vídeo deletado com sucesso: " + id));
    }

    public List<Video> findByCategoriaAndNotFavoritedByUser(Categoria categoria, String usuarioId) {
        var idsFavoritos = videoFavoritoService.getFavoritosPorUsuario(usuarioId)
                .stream()
                .map(VideoFavoritoResponse::getVideoId)
                .toList();

        return videoReativoRepositorio.findByCategoria(categoria)
                .toStream()
                .filter(video -> !idsFavoritos.contains(video.getVideoId()))
                .toList();
    }

    @Override
    public Mono<VideoDeleteResponse> deleteVideo(String videoId) {

        return videoReativoRepositorio.findById(videoId)
                .flatMap(existingVideo -> videoReativoRepositorio.deleteById(videoId)
                        .thenReturn(VideoDeleteResponse.sucesso()))
                .switchIfEmpty(Mono.just(VideoDeleteResponse.falha()));
    }


    @Override
    public Flux<Video> buscaVideoPorTitulo (String query) {
        return videoReativoRepositorio.findByTituloContainingIgnoreCase(query);
    }


}
