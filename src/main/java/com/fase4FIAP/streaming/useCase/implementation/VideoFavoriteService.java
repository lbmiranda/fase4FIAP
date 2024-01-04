package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.AlreadyFavoritedException;
import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.useCase.contract.IVideoFavoriteService;
import com.fase4FIAP.streaming.domain.dto.request.VideoFavoriteRequest;
import com.fase4FIAP.streaming.domain.dto.response.VideoFavoriteResponse;
import com.fase4FIAP.streaming.domain.model.VideoFavorite;
import com.fase4FIAP.streaming.domain.repository.VideoFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoFavoriteService implements IVideoFavoriteService {

    private final VideoFavoriteRepository videoFavoriteRepository;

    @Override
    public VideoFavoriteResponse favorite(VideoFavoriteRequest request) {
        userFavorited(request);
        return VideoFavoriteResponse.of(videoFavoriteRepository.save(VideoFavorite.of(request)));
    }

    @Override
    public List<VideoFavoriteResponse> getFavoritesByUser(String userId) {
        return videoFavoriteRepository.findByUserId(userId)
                .stream()
                .map(VideoFavoriteResponse::of)
                .toList();
    }

    @Override
    public List<VideoFavoriteResponse> getAll() {
        return videoFavoriteRepository.findAll()
                .stream()
                .map(VideoFavoriteResponse::of)
                .toList();
    }

    @Override
    public void delete(String id) {
        findById(id);
        videoFavoriteRepository.deleteById(id);
    }

    private void userFavorited(VideoFavoriteRequest request) {
        var exist = videoFavoriteRepository.findByUserIdAndVideoId(request.getUserId(), request.getVideoId());

        if (exist.isPresent()) {
            throw new AlreadyFavoritedException("O vídeo já está favoritado pelo usuário.");
        }
    }

    private void findById(String id) {
        videoFavoriteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Video favorito não encontrado com id: " + id));
    }
}
