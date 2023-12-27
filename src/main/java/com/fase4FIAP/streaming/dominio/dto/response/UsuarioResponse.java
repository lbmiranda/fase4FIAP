package com.fase4FIAP.streaming.dominio.dto.response;

import com.fase4FIAP.streaming.dominio.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private String id;
    private String nome;
    private String email;
    private String senha;

    public static UsuarioResponse of(Usuario usuario) {
        var response = new UsuarioResponse();
        copyProperties(usuario, response);
        return response;
    }
}
