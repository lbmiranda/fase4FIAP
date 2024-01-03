package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.dominio.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepositorio extends MongoRepository<Video, String> {

}
