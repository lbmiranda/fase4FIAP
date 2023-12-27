package com.fase4FIAP.streaming.dominio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoFavoritoRequest {

    @NotNull(message = "O ID do usuário não pode ser nulo.")
    private String usuarioId;

    @NotNull(message = "O ID do vídeo não pode ser nulo.")
    private String videoId;

    @NotNull(message = "A data de criação não pode ser nula.")
    private LocalDate dataCriacao;
}
