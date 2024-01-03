package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.dominio.dto.request.UsuarioRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioTest {

    @Test
    void deveConverterUsuarioRequestEmUsuario(){
        String nome = "Usuário Teste";
        String email = "contato@hotmail.com";
        String senha = "123ABC";

        UsuarioRequest usuarioResponse = new UsuarioRequest(nome, email, senha);
        Usuario usuario = Usuario.of(usuarioResponse);
        
        assertEquals(nome, usuario.getNome());
        assertEquals(email, usuario.getEmail());
        assertEquals(senha, usuario.getSenha());
    }

}
