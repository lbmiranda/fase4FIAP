package com.fase4FIAP.streaming.dominio.model;

import com.fase4FIAP.streaming.dominio.dto.request.VideoRequest;
import com.fase4FIAP.streaming.dominio.enums.Categoria;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoTest {

    @Test
    void deveConverterVideoRequestEmVideo() throws IOException {
        String titulo = "Breaking Bad";
        String descricao = "Piloto";
        String nomeArquivo = "breaking_bad.mp4";
        LocalDate dataPublicacao = LocalDate.of(2008,1,20);
        Categoria categoria = Categoria.ENTRETENIMENTO;
        String favorito = ""; // TODO VERIFICAR A LÓGICA DO FAVORITO

        VideoRequest videoRequest = new VideoRequest(titulo, descricao, nomeArquivo, dataPublicacao.toString(), categoria.getNomeCategoria(), favorito);
        Video video = Video.of(nomeArquivo.getBytes(), videoRequest);

        assertEquals(titulo, video.getTitulo());
        assertEquals(descricao, video.getDescricao());
        assertEquals(nomeArquivo, video.getNomeArquivo());
        assertEquals(dataPublicacao, video.getDataPublicacao());
        assertEquals(categoria, video.getCategoria());
    }

    @Test
    void deveValidarIncrementoDeView(){

        String videoId = "123456";
        String titulo = "Breaking Bad";
        String descricao = "Piloto";
        String nomeArquivo = "breaking_bad.mp4";
        byte[] videoData = nomeArquivo.getBytes();
        LocalDate dataPublicacao = LocalDate.of(2008,1,20);
        Categoria categoria = Categoria.ENTRETENIMENTO;
        long view = 0L;

        Video video = new Video();
        video.setVideoId(videoId);
        video.setTitulo(titulo);
        video.setDescricao(descricao);
        video.setNomeArquivo(nomeArquivo);
        video.setVideoData(videoData);
        video.setDataPublicacao(dataPublicacao);
        video.setCategoria(categoria);
        video.setView(view);

        video.incrementaView();
        video.incrementaView();
        video.incrementaView();

        assertEquals(video.getView(), 3L);
    }
}
