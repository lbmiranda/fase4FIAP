package com.fase4FIAP.streaming.dominio.enums;

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

    public static Categoria fromString(String texto) {
        for (Categoria cat : Categoria.values()) {
            if (cat.getNomeCategoria().equalsIgnoreCase(texto)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Categoria não reconhecida: " + texto);
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
