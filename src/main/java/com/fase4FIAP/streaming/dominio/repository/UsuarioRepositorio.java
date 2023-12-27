package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.dominio.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

}
