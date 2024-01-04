package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.UserService;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse create(@RequestBody @Valid UserRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public UserResponse getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable String id, @RequestBody @Valid UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
