package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.response.StatisticVideoResponse;

public interface IStatisticService {

    StatisticVideoResponse calculateStatistics();
}
