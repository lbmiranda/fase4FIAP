package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Streaming")
public class VideoModelo {

    @Id
    private String videoId;
    @Indexed
    private String titulo;
    private String descricao;
    private byte[] videoData;
    private String nomeArquivo;
    @Indexed
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Boolean favorito;

    public static VideoModelo of(byte[] videoData, VideoModeloRequest request) {
        var response = new VideoModelo();
        response.setVideoData(videoData);
        response.setDataPublicacao(LocalDate.parse(request.getDataPublicacao()));
        response.setCategoria(Categoria.fromString(request.getCategoria()));
        response.setFavorito("true".equals(request.getFavorito()));
        copyProperties(request, response);
        return response;
    }
}
