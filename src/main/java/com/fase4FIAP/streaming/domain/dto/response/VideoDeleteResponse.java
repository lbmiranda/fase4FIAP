package com.fase4FIAP.streaming.domain.dto.response;

public class VideoDeleteResponse {

    private final boolean success;


    public VideoDeleteResponse(boolean success) {
        this.success = success;
    }

    public static VideoDeleteResponse success() {
        return new VideoDeleteResponse(true);
    }

    public static VideoDeleteResponse error() {
        return new VideoDeleteResponse(false);
    }

    public boolean successfully() {
        return success;
    }
}
