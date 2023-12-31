package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.VideoService;
import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.enums.Category;
import com.fase4FIAP.streaming.domain.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> uploadVideo(@RequestPart MultipartFile file, @RequestPart VideoRequest request) {
        return videoService.uploadVideo(file, request)
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
        return videoService.getVideoContent(videoId)
                .map(videoData -> ResponseEntity.ok().body(videoData))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<Video> getAllVideos(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return videoService.getAllVideosPaginate(page, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Flux<Video>> findVideoByTitle(@RequestParam (required = false) String query) {
        Flux<Video> videos = videoService.findVideoByTitle(query);
        return ResponseEntity.ok(videos);
    }

    @ModelAttribute("categories")
    public Category[] getCategories() {
        return Category.values();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        videoService.delete(id);
    }
    @DeleteMapping("/delete/{videoId}")
    public Mono<ResponseEntity<String>> deleteVideo(@PathVariable String videoId) {
        return videoService.deleteVideo(videoId)
                .map(deleted -> {
                    if(deleted.successfully()) {
                        return ResponseEntity.ok("Vídeo deletado com sucesso!");
                    } else {
                        return ResponseEntity.badRequest().body("Erro ao deletar o vídeo");
                    }
                });
    }

}
