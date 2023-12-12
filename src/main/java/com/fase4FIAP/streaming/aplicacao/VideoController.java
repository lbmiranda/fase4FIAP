package com.fase4FIAP.streaming.aplicacao;


import com.fase4FIAP.streaming.casoDeUso.ServicoVideo;
import com.fase4FIAP.streaming.dominio.VideoModelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class VideoController {

        private final ServicoVideo servicoVideo;

        @Autowired
        public VideoController(ServicoVideo servicoVideo) {
            this.servicoVideo = servicoVideo;
        }

        @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public Mono<ResponseEntity<String>> uploadVideo(@RequestPart("arquivo") byte[] videoData,
                                                        @RequestPart("nomeArquivo") String nomeArquivo) {
            return servicoVideo.uploadVideo(videoData, nomeArquivo)
                    .map(videoId -> ResponseEntity.ok("Video cadastrado com sucesso. Video ID: " + videoId))
                    .defaultIfEmpty(ResponseEntity.badRequest().body("Falha ao cadastrar o video."));
        }

        @GetMapping("/")
        public String home() {
            return "index";
        }

        @GetMapping(value = "/{videoId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
        public Mono<ResponseEntity<byte[]>> playVideo(@PathVariable String videoId) {
            return servicoVideo.getVideoContent(videoId)
                    .map(videoData -> ResponseEntity.ok().body(videoData))
                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }

        @GetMapping("/videos")
        public String getAllVideos(Model model) {
            Flux<VideoModelo> videos = servicoVideo.getAllVideos();
            model.addAttribute("videos", videos);
            return "videoList";
        }

}
