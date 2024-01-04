package com.fase4FIAP.streaming.domain.dto.response;

import com.fase4FIAP.streaming.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String password;

    public static UserResponse of(User user) {
        var response = new UserResponse();
        copyProperties(user, response);
        return response;
    }
}
