package com.fase4FIAP.streaming.video.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Video {

    @Id
    private Long id;
    @Indexed
    private String titulo;
    private String descricao;
    private String url;
    @Indexed
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Boolean favorito;

}
