package com.fase4FIAP.streaming.dominio.dto.response;

import com.fase4FIAP.streaming.dominio.model.VideoFavorito;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoFavoritoResponse {

    private String id;
    private String usuarioId;
    private String videoId;
    private LocalDate dataCriacao;

    public static VideoFavoritoResponse of(VideoFavorito videoFavorito) {
        var response = new VideoFavoritoResponse();
        copyProperties(videoFavorito, response);
        return response;
    }
}
