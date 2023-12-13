package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class VideoUploadResult {

    private final boolean success;
    private final ObjectId videoId;
}
