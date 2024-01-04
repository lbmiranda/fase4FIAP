package com.fase4FIAP.streaming.utils;

import com.fase4FIAP.streaming.dominio.model.Usuario;

public abstract class UsuarioHelper {

    public static Usuario gerarUsuario() {
        return Usuario.builder()
                .id("123ABC")
                .nome("USER_ROLER")
                .email("contato@hotmail.com")
                .senha("25d55ad283aa400af464c76d713c07ad")
                .build();
    }
}

