package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoModeloRequest {

    private String titulo;
    private String descricao;
    private String nomeArquivo;
    private String dataPublicacao;
    private String categoria;
    private String favorito;
}
