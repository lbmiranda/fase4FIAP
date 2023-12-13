package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoModeloRequest {

    private String titulo;
    private String descricao;
    private String nomeArquivo;
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Boolean favorito;

    public static VideoModeloRequest of(VideoModelo videoModelo) {
        var response = new VideoModeloRequest();
        copyProperties(videoModelo, response);
        return response;
    }
}
