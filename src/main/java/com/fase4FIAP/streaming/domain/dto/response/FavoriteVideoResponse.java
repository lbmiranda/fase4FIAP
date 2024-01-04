package com.fase4FIAP.streaming.domain.dto.response;

import com.fase4FIAP.streaming.domain.model.FavoriteVideo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteVideoResponse {

    private String id;
    private String userId;
    private String videoId;
    private LocalDate creationDate;

    public static FavoriteVideoResponse of(FavoriteVideo favoriteVideo) {
        var response = new FavoriteVideoResponse();
        copyProperties(favoriteVideo, response);
        return response;
    }
}
