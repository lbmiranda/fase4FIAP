package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.enums.Categoria;
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
public class Video {

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
    private long view;

    public static Video of(byte[] videoData, VideoRequest request) {
        var response = new Video();
        response.setVideoData(videoData);
        response.setDataPublicacao(LocalDate.parse(request.getDataPublicacao()));
        response.setCategoria(Categoria.fromString(request.getCategoria()));
        copyProperties(request, response);
        return response;
    }

    public void incrementaView() {
        this.view += 1;
    }
}
