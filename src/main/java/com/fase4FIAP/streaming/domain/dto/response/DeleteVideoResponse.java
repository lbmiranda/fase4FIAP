package com.fase4FIAP.streaming.domain.dto.response;

public class DeleteVideoResponse {

    private final boolean success;


    public DeleteVideoResponse(boolean success) {
        this.success = success;
    }

    public static DeleteVideoResponse success() {
        return new DeleteVideoResponse(true);
    }

    public static DeleteVideoResponse error() {
        return new DeleteVideoResponse(false);
    }

    public boolean successfully() {
        return success;
    }
}
