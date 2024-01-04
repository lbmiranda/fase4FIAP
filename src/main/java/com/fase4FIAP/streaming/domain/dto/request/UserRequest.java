package com.fase4FIAP.streaming.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "O nome não pode ser nulo.")
    private String name;

    @NotNull(message = "O email não pode ser nulo.")
    @Email(message = "Email inválido.")
    private String email;

    @NotNull(message = "A senha não pode ser nula.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;
}
