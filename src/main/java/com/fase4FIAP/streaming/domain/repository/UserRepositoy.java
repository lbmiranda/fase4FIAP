package com.fase4FIAP.streaming.domain.repository;

import com.fase4FIAP.streaming.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends MongoRepository<User, String> {

}
