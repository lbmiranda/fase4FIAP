package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.model.Video;

import java.util.List;

public interface IRecommendationVideoService {

    List<Video> recommendVideos(String userId);
}
