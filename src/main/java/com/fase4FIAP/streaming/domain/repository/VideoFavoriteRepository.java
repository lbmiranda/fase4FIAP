package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.VideoFavorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoFavoriteRepository extends MongoRepository<VideoFavorite, String> {

    Optional<VideoFavorite> findByUserIdAndVideoId(String userId, String videoId);

    List<VideoFavorite> findByUserId(String userId);
}
