package com.fase4FIAP.streaming.dominio.repository;

import com.fase4FIAP.streaming.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
//@AutoConfigureTestDatabase
//@ActiveProfiles("test")
//@Transactional
@ExtendWith(MockitoExtension.class)
@DataMongoTest
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

//    @Test
//    void devePermitirCriarTabela() {
//        var totalRegisters = userRepository.count();
//        assertThat(totalRegisters).isPositive();
//
//
//    }






}
