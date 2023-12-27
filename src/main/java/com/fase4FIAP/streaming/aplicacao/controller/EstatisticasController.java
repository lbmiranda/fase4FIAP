package com.fase4FIAP.streaming.aplicacao.controller;

import com.fase4FIAP.streaming.casoDeUso.impl.EstatisticasService;
import com.fase4FIAP.streaming.dominio.dto.response.EstatisticasVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final EstatisticasService service;

    @GetMapping
    public EstatisticasVideoResponse calcularEstatisticas() {
        return service.calcularEstatisticas();
    }
}
