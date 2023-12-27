package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.dominio.model.VideoFavorito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoFavoritoRepositorio extends MongoRepository<VideoFavorito, String> {

    Optional<VideoFavorito> findByUsuarioIdAndVideoId(String usuarioId, String videoId);

    List<VideoFavorito> findByUsuarioId(String usuarioId);
}
