package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.dominio.dto.request.VideoFavoritoRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videosFavoritos")
public class VideoFavorito {

    @Id
    private String id;
    private String usuarioId;
    private String videoId;
    private LocalDate dataCriacao;

    public static VideoFavorito of(VideoFavoritoRequest request) {
        var response = new VideoFavorito();
        copyProperties(request, response);
        return response;
    }
}
