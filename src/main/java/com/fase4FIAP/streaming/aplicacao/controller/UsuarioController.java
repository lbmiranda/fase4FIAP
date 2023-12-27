package com.fase4FIAP.streaming.aplicacao.controller;

import com.fase4FIAP.streaming.casoDeUso.impl.UsuarioService;
import com.fase4FIAP.streaming.dominio.dto.request.UsuarioRequest;
import com.fase4FIAP.streaming.dominio.dto.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<UsuarioResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public UsuarioResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public UsuarioResponse update(@PathVariable String id, @RequestBody UsuarioRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
