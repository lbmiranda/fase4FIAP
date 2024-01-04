package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.FavoriteVideoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "videosFavoritos")
public class FavoriteVideo {

    @Id
    private String id;
    private String userId;
    private String videoId;
    private LocalDate creationDate;

    public static FavoriteVideo of(FavoriteVideoRequest request) {
        var response = new FavoriteVideo();
        copyProperties(request, response);
        return response;
    }
}
