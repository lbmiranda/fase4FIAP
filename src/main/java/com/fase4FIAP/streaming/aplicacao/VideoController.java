package com.fase4FIAP.streaming.aplicacao;

import com.fase4FIAP.streaming.casoDeUso.ServicoVideo;
import com.fase4FIAP.streaming.dominio.VideoModelo;
import com.fase4FIAP.streaming.dominio.VideoModeloRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideoController {

    private final ServicoVideo servicoVideo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> uploadVideo(@RequestPart MultipartFile arquivo,
                                                    @RequestPart VideoModeloRequest request) {
        return servicoVideo.uploadVideo(arquivo, request)
                .map(result -> {
                    if (result.isSuccess()) {
                        return ResponseEntity.ok("Video cadastrado com sucesso. Video ID: " + result.getVideoId());
                    } else {
                        return ResponseEntity.badRequest().body("Falha ao cadastrar o video.");
                    }
                });
    }

    @GetMapping(value = "play/{videoId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> playVideo(@PathVariable String videoId) {
        return servicoVideo.getVideoContent(videoId)
                .map(videoData -> ResponseEntity.ok().body(videoData))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<VideoModelo> getAllVideos() {
        return servicoVideo.getAllVideos();
    }
}
