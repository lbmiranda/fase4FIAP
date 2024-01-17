package com.fase4FIAP.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamingApplication.class, args);
    }

    static void runApplication(Class<?> applicationClass, String[] args) {
        SpringApplication.run(applicationClass, args);
    }

}
