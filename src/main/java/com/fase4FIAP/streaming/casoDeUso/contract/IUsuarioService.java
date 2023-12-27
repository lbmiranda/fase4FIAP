package com.fase4FIAP.streaming.casoDeUso.contract;

import com.fase4FIAP.streaming.dominio.dto.request.UsuarioRequest;
import com.fase4FIAP.streaming.dominio.dto.response.UsuarioResponse;

import java.util.List;

public interface IUsuarioService {

    UsuarioResponse create(UsuarioRequest request);

    List<UsuarioResponse> getAll();

    UsuarioResponse getById(String id);

    UsuarioResponse update(String id, UsuarioRequest request);

    void delete(String id);
}
