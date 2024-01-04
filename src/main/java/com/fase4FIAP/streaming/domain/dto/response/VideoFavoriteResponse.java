package com.fase4FIAP.streaming.domain.dto.response;

import com.fase4FIAP.streaming.domain.model.VideoFavorite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoFavoriteResponse {

    private String id;
    private String userId;
    private String videoId;
    private LocalDate creationDate;

    public static VideoFavoriteResponse of(VideoFavorite videoFavorite) {
        var response = new VideoFavoriteResponse();
        copyProperties(videoFavorite, response);
        return response;
    }
}
