package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideoUploadResult {

    private final boolean success;
    private final String videoId;
}
