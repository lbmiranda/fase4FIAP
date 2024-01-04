package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;

    public static User of(UserRequest request) {
        var response = new User();
        copyProperties(request, response);
        return response;
    }
}
