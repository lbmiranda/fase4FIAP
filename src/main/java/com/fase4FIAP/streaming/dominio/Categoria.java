package com.fase4FIAP.streaming.dominio;

public enum Categoria {

    MUSICA("Musica"),
    EDUCACAO("Educação"),
    ENTRETENIMENTO("Entretenimento"),
    ESPORTES("Esportes"),
    NOTICIAS("Notícias"),
    OUTROS("Outros");

    private final String nomeCategoria;

    Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
