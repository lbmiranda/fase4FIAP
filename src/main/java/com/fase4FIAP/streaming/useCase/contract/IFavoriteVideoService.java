package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;

import java.util.List;

public interface IFavoriteVideoService {

    FavoriteVideoResponse favorite(FavoriteVideoRequest request);

    List<FavoriteVideoResponse> getFavoritesByUser(String userId);

    List<FavoriteVideoResponse> getAll();

    void delete(String id);
}
