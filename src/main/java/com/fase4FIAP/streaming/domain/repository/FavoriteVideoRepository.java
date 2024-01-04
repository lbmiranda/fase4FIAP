package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteVideoRepository extends MongoRepository<FavoriteVideo, String> {

    Optional<FavoriteVideo> findByUserIdAndVideoId(String userId, String videoId);

    List<FavoriteVideo> findByUserId(String userId);
}
