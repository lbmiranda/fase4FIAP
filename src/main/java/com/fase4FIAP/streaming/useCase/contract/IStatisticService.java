package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.response.VideoStatisticResponse;

public interface IStatisticService {

    VideoStatisticResponse calculateStatistics();
}
