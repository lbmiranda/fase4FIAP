package com.fase4FIAP.streaming.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequest {

    private String title;
    private String description;
    private String fileName;
    private String publicationDate;
    private String category;
    private String favorite;



}
