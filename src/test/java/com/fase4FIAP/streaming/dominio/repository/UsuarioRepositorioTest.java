package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.dominio.model.Usuario;
import com.fase4FIAP.streaming.utils.UsuarioHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class UsuarioRepositorioTest {

    final String id = "c1229ad8-e869-4cf0-bf1b-de193465248b";

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    AutoCloseable openMocks;

    @BeforeEach
    void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class RegisterUser {
        @Test
        void devePermitirCriarUsuario(){
            var user = UsuarioHelper.gerarUsuario();
            when(usuarioRepositorio.save(any(Usuario.class))).thenReturn(user);

            var userCreated = usuarioRepositorio.save(user);

            assertThat(userCreated).isNotNull().isEqualTo(user);
            verify(usuarioRepositorio, times(1)).save(any(Usuario.class));
        }

    }

    @Nested
    class DeleteUser {

        @Test
        void devePermitirExcluirUsuario() {
            doNothing().when(usuarioRepositorio).deleteById(any(String.class));

            usuarioRepositorio.deleteById(id);

            verify(usuarioRepositorio, times(1)).deleteById(any(String.class));
        }
    }

    @Nested
    class SearchUser {
        @Test
        void devePermitirBuscarUsuario(){
            var user = UsuarioHelper.gerarUsuario();
            when(usuarioRepositorio.findById(any(String.class))).thenReturn(Optional.of(user));

            var userFilter = usuarioRepositorio.findById(id);

            assertThat(userFilter).isPresent().containsSame(user);
            userFilter.ifPresent(search -> {
                assertThat(search.getId()).isEqualTo(user.getId());
                assertThat(search.getNome()).isEqualTo(user.getNome());
                assertThat(search.getEmail()).isEqualTo(user.getEmail());
                assertThat(search.getSenha()).isEqualTo(user.getSenha());
            });

            verify(usuarioRepositorio, times(1)).findById(any(String.class));

        }

    }

}
