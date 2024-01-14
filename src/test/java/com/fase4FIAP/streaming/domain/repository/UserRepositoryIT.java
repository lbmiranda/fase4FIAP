package com.fase4FIAP.streaming.domain.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@DataMongoTest
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    void allowCreateTable() {
        var totalRegisters = userRepository.count();
        assertThat(totalRegisters).isPositive();


    }






}
