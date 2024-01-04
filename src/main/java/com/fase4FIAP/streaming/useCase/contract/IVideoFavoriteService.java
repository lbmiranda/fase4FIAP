package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.request.VideoFavoriteRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoFavoriteResponse;

import java.util.List;

public interface IVideoFavoriteService {

    VideoFavoriteResponse favorite(VideoFavoriteRequest request);

    List<VideoFavoriteResponse> getFavoritesByUser(String userId);

    List<VideoFavoriteResponse> getAll();

    void delete(String id);
}
