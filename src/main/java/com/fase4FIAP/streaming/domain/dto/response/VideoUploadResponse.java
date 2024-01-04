package com.fase4FIAP.streaming.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoUploadResponse {

    private final boolean success;
    private final String videoId;
}
