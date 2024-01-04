package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.AlreadyFavoritedException;
import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.useCase.contract.IFavoriteVideoService;
import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import com.fase4FIAP.streaming.domain.dto.response.FavoriteVideoResponse;
import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import com.fase4FIAP.streaming.domain.repository.FavoriteVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteVideoService implements IFavoriteVideoService {

    private final FavoriteVideoRepository favoriteVideoRepository;

    @Override
    public FavoriteVideoResponse favorite(FavoriteVideoRequest request) {
        userFavorites(request);
        return FavoriteVideoResponse.of(favoriteVideoRepository.save(FavoriteVideo.of(request)));
    }

    @Override
    public List<FavoriteVideoResponse> getFavoritesByUser(String userId) {
        return favoriteVideoRepository.findByUserId(userId)
                .stream()
                .map(FavoriteVideoResponse::of)
                .toList();
    }

    @Override
    public List<FavoriteVideoResponse> getAll() {
        return favoriteVideoRepository.findAll()
                .stream()
                .map(FavoriteVideoResponse::of)
                .toList();
    }

    @Override
    public void delete(String id) {
        findById(id);
        favoriteVideoRepository.deleteById(id);
    }

    private void userFavorites(FavoriteVideoRequest request) {
        var exist = favoriteVideoRepository.findByUserIdAndVideoId(request.getUserId(), request.getVideoId());

        if (exist.isPresent()) {
            throw new AlreadyFavoritedException("O vídeo já está favoritado pelo usuário.");
        }
    }

    private void findById(String id) {
        favoriteVideoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video favorito não encontrado com id: " + id));
    }
}
