package com.fase4FIAP.streaming.application.controller;

import com.fase4FIAP.streaming.domain.dto.response.VideoStatisticResponse;
import com.fase4FIAP.streaming.useCase.implementation.StatisticService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StatisticControllerTest {

    final String baseUrl = "/statistic";

    private MockMvc mockMvc;

    @Mock
    private StatisticService statisticService;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        StatisticController statisticController = new StatisticController(statisticService);
        mockMvc = MockMvcBuilders.standaloneSetup(StatisticControllerTest.this.statisticService)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    // TODO - Ainda pendente
    @Test
    void allowCalculateStatistic() throws Exception {
        var videoStatisticResponse = new VideoStatisticResponse(3L, 3L, 5);
        Mockito.when(statisticService.calculateStatistics()).thenReturn(videoStatisticResponse);

        mockMvc.perform(get(baseUrl)).andExpect(status().isOk());
        verify(statisticService, times(1)).calculateStatistics();
    }

}
