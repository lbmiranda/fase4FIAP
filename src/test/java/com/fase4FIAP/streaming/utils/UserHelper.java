package com.fase4FIAP.streaming.utils;

import com.fase4FIAP.streaming.domain.model.User;

public abstract class UserHelper {

    public static User createUser() {
        return User.builder()
                .id("123ABC")
                .name("USER_ROLLER")
                .email("contato@hotmail.com")
                .password("25d55ad283aa400af464c76d713c07ad")
                .build();
    }
}

