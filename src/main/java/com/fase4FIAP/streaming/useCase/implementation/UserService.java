package com.fase4FIAP.streaming.useCase.implementation;

import com.fase4FIAP.streaming.application.exceptions.NotFoundException;
import com.fase4FIAP.streaming.domain.repository.UserRepository;
import com.fase4FIAP.streaming.useCase.contract.IUserService;
import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import com.fase4FIAP.streaming.domain.dto.response.UserResponse;
import com.fase4FIAP.streaming.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        return UserResponse.of(userRepository.save(User.of(request)));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
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
        var user = findById(id);
        copyProperties(request, user, "id");
        return UserResponse.of(userRepository.save(user));
    }

    @Override
    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado com o id: " + id));
    }
}
