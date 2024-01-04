package com.fase4FIAP.streaming.domain.model;

import com.fase4FIAP.streaming.domain.dto.request.VideoRequest;
import com.fase4FIAP.streaming.domain.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Streaming")
public class Video {

    @Id
    private String videoId;
    @Indexed
    private String title;
    private String description;
    private byte[] videoData;
    private String fileName;
    @Indexed
    private LocalDate publicationDate;
    private Category category;
    private long view;

    public static Video of(byte[] videoData, VideoRequest request) {
        var response = new Video();
        response.setVideoData(videoData);
        response.setPublicationDate(LocalDate.parse(request.getPublicationDate()));
        response.setCategory(Category.fromString(request.getCategory()));
        copyProperties(request, response);
        return response;
    }

    public void incrementView() {
        this.view += 1;
    }
}
