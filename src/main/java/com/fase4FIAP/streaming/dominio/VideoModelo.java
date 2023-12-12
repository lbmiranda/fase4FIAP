package com.fase4FIAP.streaming.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "Streaming")
public class VideoModelo {

        @Id
        private ObjectId videoId;
        @Indexed
        private String titulo;
        private String descricao;
        private String url;
        private byte[] videoData;
        private String nomeArquivo;
        @Indexed
        private LocalDate dataPublicacao;
        private Categoria categoria;
        private Boolean favorito;

        public VideoModelo(byte[] videoData, String nomeArquivo) {
                this.videoData = videoData;
                this.nomeArquivo = nomeArquivo;
        }

        @Override
        public String toString() {
                return "VideoModelo{" +
                        "videoId=" + videoId +
                        ", titulo='" + titulo + '\'' +
                        ", descricao='" + descricao + '\'' +
                        ", url='" + url + '\'' +
                        ", nomeArquivo='" + nomeArquivo + '\'' +
                        ", dataPublicacao=" + dataPublicacao +
                        ", categoria=" + categoria +
                        ", favorito=" + favorito +
                        '}';
        }
}
