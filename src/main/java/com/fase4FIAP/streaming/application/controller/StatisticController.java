package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.useCase.implementation.StatisticService;
import com.fase4FIAP.streaming.domain.dto.response.StatisticVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estatisticas")
public class StatisticController {

    private final StatisticService service;

    @GetMapping
    public StatisticVideoResponse calculateStatistics() {
        return service.calculateStatistics();
    }
}
