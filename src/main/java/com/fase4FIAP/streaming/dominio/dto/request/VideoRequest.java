package com.fase4FIAP.streaming.dominio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {

    private String titulo;
    private String descricao;
    private String nomeArquivo;
    private String dataPublicacao;
    private String categoria;
    private String favorito;
}
