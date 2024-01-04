package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {

}
