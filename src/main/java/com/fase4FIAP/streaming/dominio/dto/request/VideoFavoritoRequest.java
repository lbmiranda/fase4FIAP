package com.fase4FIAP.streaming.dominio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoFavoritoRequest {

    private String usuarioId;
    private String videoId;
    private LocalDate dataCriacao;
}
