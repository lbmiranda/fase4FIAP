package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.VideoModelo;
import com.fase4FIAP.streaming.dominio.VideoRepositorio;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicoVideo implements StreamingVideo {

    private final VideoRepositorio videoRepositorio;

    public ServicoVideo (VideoRepositorio videoRepositorio) {
        this.videoRepositorio = videoRepositorio;
    }

    @Override
    public Mono<ObjectId> uploadVideo(byte[] videoData, String nomeArquivo) {

        VideoModelo video = new VideoModelo (videoData, nomeArquivo);

        return videoRepositorio.save(video)
                .map(VideoModelo :: getVideoId);
    }

    @Override
    public Mono<byte[]> getVideoContent(String videoId) {
        return videoRepositorio.findById(videoId)
                .map(VideoModelo::getVideoData);
    }

    @Override
    public Flux<VideoModelo> getAllVideos() {
        return videoRepositorio.findAll();
    }


}
