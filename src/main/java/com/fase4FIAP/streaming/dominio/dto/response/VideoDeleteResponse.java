package com.fase4FIAP.streaming.dominio.dto.response;

public class VideoDeleteResponse {

    private final boolean sucesso;


    public VideoDeleteResponse(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public static VideoDeleteResponse sucesso() {
        return new VideoDeleteResponse(true);
    }

    public static VideoDeleteResponse falha() {
        return new VideoDeleteResponse(false);
    }

    public boolean comSucesso() {
        return sucesso;
    }
}
