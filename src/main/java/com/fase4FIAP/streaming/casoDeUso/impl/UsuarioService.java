package com.fase4FIAP.streaming.casoDeUso.impl;

import com.fase4FIAP.streaming.aplicacao.exceptions.NotFoundException;
import com.fase4FIAP.streaming.casoDeUso.contract.IUsuarioService;
import com.fase4FIAP.streaming.dominio.dto.request.UsuarioRequest;
import com.fase4FIAP.streaming.dominio.dto.response.UsuarioResponse;
import com.fase4FIAP.streaming.dominio.model.Usuario;
import com.fase4FIAP.streaming.dominio.repository.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    public UsuarioResponse create(UsuarioRequest request) {
        return UsuarioResponse.of(usuarioRepositorio.save(Usuario.of(request)));
    }

    @Override
    public List<UsuarioResponse> getAll() {
        return usuarioRepositorio.findAll()
                .stream()
                .map(UsuarioResponse::of)
                .toList();
    }

    @Override
    public UsuarioResponse getById(String id) {
        return UsuarioResponse.of(findById(id));
    }

    @Override
    public UsuarioResponse update(String id, UsuarioRequest request) {
        var usuario = findById(id);
        copyProperties(request, usuario, "id");
        return UsuarioResponse.of(usuarioRepositorio.save(usuario));
    }

    @Override
    public void delete(String id) {
        findById(id);
        usuarioRepositorio.deleteById(id);
    }

    private Usuario findById(String id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado com o id: " + id));
    }
}
