package com.fase4FIAP.streaming.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteVideoRequest {

    @NotNull(message = "O ID do usuário não pode ser nulo.")
    private String userId;

    @NotNull(message = "O ID do vídeo não pode ser nulo.")
    private String videoId;

    @NotNull(message = "A data de criação não pode ser nula.")
    private LocalDate creationDate;
}
