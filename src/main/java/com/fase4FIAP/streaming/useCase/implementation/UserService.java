package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.useCase.contract.IUserService;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import com.fase4FIAP.streaming.domain.model.User;
import com.fase4FIAP.streaming.domain.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepositoy userRepositoy;

    @Override
    public UserResponse create(UserRequest request) {
        return UserResponse.of(userRepositoy.save(User.of(request)));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepositoy.findAll()
                .stream()
                .map(UserResponse::of)
                .toList();
    }

    @Override
    public UserResponse getById(String id) {
        return UserResponse.of(findById(id));
    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        var usuario = findById(id);
        copyProperties(request, usuario, "id");
        return UserResponse.of(userRepositoy.save(usuario));
    }

    @Override
    public void delete(String id) {
        findById(id);
        userRepositoy.deleteById(id);
    }

    private User findById(String id) {
        return userRepositoy.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado com o id: " + id));
    }
}
