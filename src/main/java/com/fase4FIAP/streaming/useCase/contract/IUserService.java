package com.fase4FIAP.streaming.useCase.contract;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse create(UserRequest request);

    List<UserResponse> getAll();

    UserResponse getById(String id);

    UserResponse update(String id, UserRequest request);

    void delete(String id);
}
