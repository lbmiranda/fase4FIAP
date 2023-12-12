package com.fase4FIAP.streaming.casoDeUso;

import com.fase4FIAP.streaming.dominio.VideoModelo;
import org.bson.types.ObjectId;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StreamingVideo {

    Mono<ObjectId> uploadVideo(byte[] videoData, String nomeArquivo);
    Mono<byte[]> getVideoContent(String videoId);
    Flux<VideoModelo> getAllVideos();

}
