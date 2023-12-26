package com.fase4FIAP.streaming.dominio;

public class VideoDeleteResult {

    private final boolean success;

    private VideoDeleteResult(boolean success) {
        this.success = success;
    }

    public static VideoDeleteResult success() {
        return new VideoDeleteResult(true);
    }

    public static VideoDeleteResult failure() {
        return new VideoDeleteResult(false);
    }

    public boolean isSuccess() {
        return success;
    }
}
