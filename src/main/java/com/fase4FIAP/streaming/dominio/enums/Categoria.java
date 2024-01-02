package com.fase4FIAP.streaming.dominio.enums;

public enum Categoria {

    MUSICA("Musica"),
    EDUCACAO("Educacao"),
    ENTRETENIMENTO("Entretenimento"),
    ESPORTES("Esportes"),
    NOTICIAS("Noticias"),
    OUTROS("Outros");

    private final String nomeCategoria;

    Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public static Categoria fromString(String texto) {
        String textoArrumado = texto.trim();
        for (Categoria cat : Categoria.values()) {
            if (cat.getNomeCategoria().equalsIgnoreCase(textoArrumado)) {
                return cat;
            }
        }
        throw new IllegalArgumentException("Categoria não reconhecida: " + textoArrumado);
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
