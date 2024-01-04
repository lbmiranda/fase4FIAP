package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.VideoFavoriteRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videosFavoritos")
public class VideoFavorite {

    @Id
    private String id;
    private String userId;
    private String videoId;
    private LocalDate creationDate;

    public static VideoFavorite of(VideoFavoriteRequest request) {
        var response = new VideoFavorite();
        copyProperties(request, response);
        return response;
    }
}
